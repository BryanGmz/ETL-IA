import Limpiar as moduloLimpiar
import sys
import pandas as pd
import numpy as np
from datetime import datetime
from peewee import *

limpiar = moduloLimpiar
db = PostgresqlDatabase('repository_etl', user='bryan', password='Manager1', host='localhost', port=5432)

class Persona(Model):
    id_persona = AutoField()
    dpi = IntegerField(unique = True)
    primer_nombre = CharField(max_length = 50)
    segundo_nombre = CharField(max_length = 50)
    primer_apellido = CharField(max_length = 50)
    segundo_apellido = CharField(max_length = 50)
    apellido_casada = CharField(max_length = 50)
    nit = IntegerField(unique = True)
    genero = CharField(max_length = 50)
    orden_cedula = CharField(max_length = 50, null = True)
    registro_cedula = CharField(max_length = 50, null = True)
    direccion_residencia = CharField(max_length = 150, null = True)
    telefono = IntegerField(null = True)
    email = CharField(max_length = 50, null = True)
    fecha_nacimiento = DateField(null = True)

    class Meta: 
        database = db

class Empresa(Model):
    id_empresa = AutoField()
    nombre_empresa = CharField(max_length = 100)
    nit = IntegerField(unique = True)
    codigo = IntegerField(unique = True)
    direccion = CharField(null = True, max_length = 150)
    telefono = IntegerField(null = True)

    class Meta: 
        database = db

class Trabajo(Model): 
    id_trabajo = AutoField()
    id_persona = ForeignKeyField(Persona, backref='trabajos')
    id_empresa = ForeignKeyField(Empresa, backref='trabajos')
    fecha_incial = DateField()
    fecha_final = DateField()
    nombre_puesto = CharField(max_length = 100, null = True)
    mes_planilla = CharField(max_length = 50, null = True)
    salario = DecimalField(max_digits=8, decimal_places=2)

    class Meta: 
        database = db

def crear_database():
    db.connect()
    db.create_tables([Persona, Empresa, Trabajo])


def obtener_o_crear_persona(row, data_frame  : pd.DataFrame):
    try:
        # Buscar si existe el empleado
        empleado = Persona.get(Persona.dpi == int(row['dpi']))
        # moduloLimpiar.agregar_errores(int(int(index) + 1), "Ya existe un Empleado con el DPI: " + str(empleado.dpi), True, "dpi", "Duplicacion de Datos")
    except:
        try:
            empleado = Persona.get(Persona.dpi == int(row['nit']))
        except:
            empleado = Persona (
                dpi = int(row['dpi']),
                primer_nombre = str(row['primer_nombre']),
                segundo_nombre = str(row['segundo_nombre']),
                primer_apellido = str(row['primer_apellido']),
                segundo_apellido = str(row['segundo_apellido']),
                apellido_casada = str(row['apellido_casada']),
                nit = int(row['nit']),
                genero = str(row['genero']),
                orden_cedula = (comprobar_existencia_columnas(row, 'cedula_orden', data_frame)),
                registro_cedula = (comprobar_existencia_columnas(row, 'cedula_registro', data_frame)),
                direccion_residencia = (comprobar_existencia_columnas(row, 'direccion_residencia', data_frame)),
                telefono = (comprobar_existencia_columnas(row, 'telefono', data_frame)),
                email = (comprobar_existencia_columnas(row, 'correo_electronico', data_frame)),
                fecha_nacimiento = comprobar_existencia_columnas(row, 'fecha_nacimiento', data_frame)
            )
            empleado.save()
    return empleado

def obtener_o_crear_empresa(nombre, nit, codigo, direccion = None, telefono = None):
    try:
        empresa = Empresa.get(Empresa.nit == int(nit))
    except:
        try:
            empresa = Empresa.get(Empresa.codigo == int(codigo))
        except:
            empresa = Empresa (
                nombre_empresa = str(nombre),
                nit = int(nit),
                codigo = int(codigo),
                direccion = (direccion),
                telefono = (telefono),
            )
            empresa.save()
    return empresa


def crear_contrato(data_frame : pd.DataFrame): 
    for index, row in data_frame.iterrows(): 
        empleado = obtener_o_crear_persona(row, data_frame)
        empresa = obtener_o_crear_empresa(row['nombre_empresa'], row['nit_empresa'], row['codigo_unico_empresa'], comprobar_existencia_columnas(row, 'direccion_empresa', data_frame), comprobar_existencia_columnas(row, 'telefono_empresa', data_frame))
        try:
            trabajo = Trabajo.get(Trabajo.id_persona == empleado, Trabajo.fecha_incial == row['fecha_inicial'], Trabajo.id_empresa == empresa)
            moduloLimpiar.agregar_errores(int(int(index) + 1), "Ya existe un contrato del empleado con el DPI: " + str(empleado.dpi) + ", con la fecha inicial: " + str(row['fecha_inicial']) + " y en la empresa: " + str(empresa.nombre_empresa), True, "*", "Duplicacion de Datos")
        except:
            trabajo = Trabajo (
                id_persona = empleado.id_persona,
                id_empresa = empresa.id_empresa,
                fecha_incial = row['fecha_inicial'],
                fecha_final = row['fecha_final'],
                nombre_puesto = comprobar_existencia_columnas(row, 'nombre_puesto', data_frame),
                mes_planilla = comprobar_existencia_columnas(row, 'mes_planilla', data_frame),
                salario = round(float(row['salario']), 2)
            )
            trabajo.save()

# Obteniendo el path del archivo

def comprobar_existencia_columnas(row, column, data_frame : pd.DataFrame):
    if column in data_frame.columns: 
        return row[column]
    else: 
        return None

pathArchivoCSV = ""
index = 1

while(True):
    try:
        if (index == 1):
            pathArchivoCSV += sys.argv[index]
        else:
            pathArchivoCSV += " " + sys.argv[index]
        index += 1
    except:
        break

data_empresa = limpiar.obtener_csv(pathArchivoCSV.replace("\"", ""))

data_frame = limpiar.obtener_data_frame(data_empresa)
data_frame = limpiar.limpiar_enteros(data_frame)
data_frame = limpiar.limpiar_caracter_especial(data_frame, "genero", "Masculino", "Femenino", 'm', 'M', 'f', 'F', True)
data_frame = limpiar.limpiar_caracter_especial(data_frame, "condicion_laboral", "Activo", "Inactivo", 'a', 'A', 'i', 'I', False)
data_frame = limpiar.limpiar_fechas(data_frame, 'fecha_inicial', True)
data_frame = limpiar.limpiar_fechas(data_frame,'fecha_final', True)
if 'fecha_nacimiento' in data_frame.columns:
    data_frame = limpiar.limpiar_fechas(data_frame, 'fecha_nacimiento', True)

crear_database()
crear_contrato(data_frame)
print("Errores:")
print(limpiar.errores) 