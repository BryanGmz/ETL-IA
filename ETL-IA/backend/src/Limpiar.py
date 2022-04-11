import sys
import re
import pandas as pd
import numpy as np
from datetime import datetime

from sqlalchemy import null

# Log Errores
errores = ""

# Obtiene el archivo CSV, si no se le pasa el parametro de separacion por defecto utilizara la coma
def obtener_csv(nombre_archivo, separacion = ','):
    return pd.read_csv(nombre_archivo, sep = separacion)

def obtener_data_frame(data):
    return pd.DataFrame(data = data)

# Agregar errores
def agregar_errores(numero_registro, error : str, is_error_empleados, column, tipo):
    global errores
    error = "\nRegistro [" + str(numero_registro) + "]\n\tColumna [" + column + "]\n\tTipo de Error: " + tipo + "\n\tError: " + error + "\n"
    errores += error

# Limpiar datos 
def limpiar_enteros(data_frame : pd.DataFrame):
    data_frame_limpio : pd.DataFrame = data_frame
    for column in data_frame_limpio:
        values_tmp = []
        if (column == "nit" or column == "dpi" or column == "telefono" or column == "codigo_unico_empresa" or column == "telefono_empresa" or column == "nit_empresa" or column == "dpi_empleado"):
            i = 1
            for value in data_frame_limpio[column]:
                valor = str(value).replace(',', '').replace('-','').replace(' ', '')
                valor = re.sub('[a-zA-Z]', '', valor)
                valor = round(float(str(valor)))
                comprobar_error(valor, comporbar_tipo(value), column, i)
                values_tmp.append(valor)
                i += 1
            data_frame_limpio[column] = values_tmp
        elif (column == "salario"):
            i = 1
            for value in data_frame_limpio[column]:
                valor = str(value).replace(',', '').replace('-','').replace(' ', '')
                valor = re.sub('[a-zA-Z]', '', valor)
                valor = round(float(str(valor)), 2)
                comprobar_error(valor, comporbar_tipo(value), column, i)
                values_tmp.append(valor)
                i += 1
            data_frame_limpio[column] = values_tmp
    return data_frame_limpio

# Param column_evaluar: Columna a la que hay que evaluar: ej. genero y condicion_laboral
# Param primer_valor: Primer valor a comparar ej. Masculino o Activo 
# Param segundo_valor: Segundo valor a comparar ej. Femenino o Inactivo
# Param primer_caracter_min: Primer caracter a comparar en minuscula ej. m o a
# Param primer_caracter_may: Primer caracter a comparar en mayuscula ej. M o A
# Param segundo_caracter_min: Segundo caracter a comparar en minuscula ej. f o i
# Param segundo_caracter_may: Segundo caracter a comparar en mayuscula ej. F o I
# Param is_empleado: si el frame a evaluar es de empleado
def limpiar_caracter_especial(data_frame : pd.DataFrame, column_evaluar, primer_valor, segundo_valor, primer_caracter_min, primer_caracter_may, segundo_caracter_min, segundo_caracter_may, is_empleado): 
    data_frame_limpio : pd.DataFrame = data_frame
    for column in data_frame_limpio:
        values_tmp = []
        if (column == column_evaluar):
            i = 1
            for value in data_frame_limpio[column]:
                if (str(value) == primer_valor or str(value) == segundo_valor): 
                    values_tmp.append(str(value).replace(' ', ''))
                else:  
                    if (str(value) == primer_caracter_may or str(value) == primer_caracter_min 
                    or str(value)[0] == primer_caracter_may or str(value)[0] == primer_caracter_min):
                        values_tmp.append(primer_valor)
                        agregar_errores(i, str(value), is_empleado, column, "Sintaxis, se sustituyo => " + str(value) + ", por " + primer_valor)
                    elif (str(value) == segundo_caracter_may or str(value) == segundo_caracter_min 
                    or str(value)[0] == segundo_caracter_may or str(value)[0] == segundo_caracter_min):
                        values_tmp.append(segundo_valor)
                        agregar_errores(i, str(value), is_empleado, column, "Sintaxis, se sustituyo => " + str(value) + ", por " + segundo_valor)
                i += 1
            data_frame_limpio[column] = values_tmp
    return data_frame_limpio

# Param column_evaluar: Columna a la que hay que evaluar: ej. genero y condicion_laboral 
def limpiar_fechas(data_frame : pd.DataFrame, column_evaluar, is_error_empleado):
    data_frame_limpio : pd.DataFrame = data_frame
    for column in data_frame_limpio:
        values_tmp = []
        if (column == column_evaluar):
            i = 1
            for value in data_frame_limpio[column]:
                valor = str(value).replace(',', '').replace('-','/').replace(' ', '')
                valor = parsear_fecha(valor)
                comprobar_error_fecha(valor, str(value), is_error_empleado, column, i)
                values_tmp.append(valor)
                i += 1      
            data_frame_limpio[column] = values_tmp
    return data_frame_limpio

def parsear_fecha(fecha):
    salir = False
    fecha_dt : datetime
    contador = 1
    while(salir == False):
        try: 
            if (contador == 1):
                fecha_dt = datetime.strptime(fecha, '%d/%m/%Y')
            elif (contador == 2):
                fecha_dt = datetime.strptime(fecha, '%m/%d/%Y')
            elif (contador == 3):
                fecha_dt = datetime.strptime(fecha, '%Y/%m/%d')
            elif (contador == 4):
                fecha_dt = datetime.strptime(fecha, '%Y/%d/%m')
            elif (contador == 5):
                fecha_dt = datetime.strptime(fecha, '%d/%Y/%m')
            elif (contador == 6):
                fecha_dt = datetime.strptime(fecha, '%m/%Y/%d')
            else: 
                fecha_dt = null
            salir = True
        except:
            contador += 1
    return convertir_fecha_formato(fecha_dt)

def convertir_fecha_formato(fecha : datetime):
    return fecha.strftime("%d/%m/%Y")

# Comprobar fecha parseada
def comprobar_error_fecha(fecha_parseada, valor_entrada, is_error_empleado, column, registro):
    if (str(fecha_parseada) != str(valor_entrada)):
        agregar_errores(registro, str(valor_entrada), is_error_empleado, column, "Sintaxis, se sustituyo => " + str(valor_entrada) + ", por " + str(fecha_parseada))

# Comprueba el error
def comprobar_error(valor_actual, valor_anterior, column, registro):
    if (column == "nit" or column == "dpi" or column == "telefono"):
        if (valor_actual != valor_anterior):
            agregar_errores(registro, str(valor_anterior), True, column, "Sintaxis")
    else:
        if (valor_actual != valor_anterior):
            agregar_errores(registro, str(valor_anterior), False, column, "Sintaxis")

def comporbar_tipo(value):
    try:
        return float(value)
    except:
        return value