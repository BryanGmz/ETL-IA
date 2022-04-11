/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia.manejadores;

import com.gt.etl.ia.dto.Empresa;
import com.gt.etl.ia.dto.Persona;
import com.gt.etl.ia.dto.Trabajo;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bryan
 */
public class AgregarDatosTabla {
    
    private static AgregarDatosTabla agregarDatosTabla;
    
    private AgregarDatosTabla () {}
    
    public static AgregarDatosTabla getAgregarDatosTabla() {
        if (agregarDatosTabla == null) {
            agregarDatosTabla = new AgregarDatosTabla();
        } return agregarDatosTabla;
    }
    
    public void agregarDatosTablaEmpleados(JTable tabla, List<Persona> personas) {
        DefaultTableModel model = new DefaultTableModel(){
             @Override
             public boolean isCellEditable(int rowIndex,int columnIndex){
                 return false;
             }
        };
        model.addColumn("ID");
        model.addColumn("DPI");
        model.addColumn("Primer Nombre");
        model.addColumn("Segundo Nombre");
        model.addColumn("Primer Apellido");
        model.addColumn("Segundo Apellido");
        model.addColumn("Apellido Casada");
        model.addColumn("NIT");
        model.addColumn("Genero");
        model.addColumn("Orden Cedula");
        model.addColumn("Registro Cedula");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
        model.addColumn("E-mail");
        model.addColumn("Fecha Nacimiento");
        for (Persona persona : personas) {
            model.addRow(new Object[]{
                persona.getIdPersona(),
                persona.getDpiPersona(),
                persona.getPrimerNombre(),
                persona.getSegundoNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getApellidoCasada(),
                persona.getNit(),
                persona.getGenero(),
                persona.getOrdenCedula() == null ? "" : persona.getOrdenCedula(),
                persona.getRegistroCedula() == null ? "" : persona.getRegistroCedula(),
                persona.getDireccionResidencia() == null ? "" : persona.getDireccionResidencia(),
                persona.getTelefono() == null ? "" : persona.getTelefono(),
                persona.getEmail() == null ? "" : persona.getEmail(),
                persona.getFechaNacimiento() == null ? "" : persona.getFechaNacimiento()
            });
        }
        tabla.setModel(model);
    }
    
    public void agregarDatosTablaEmpresa(JTable tabla, List<Empresa> empresas) {
        DefaultTableModel model = new DefaultTableModel(){
             @Override
             public boolean isCellEditable(int rowIndex,int columnIndex){
                 return false;
             }
        };
        model.addColumn("ID");
        model.addColumn("Empresa");
        model.addColumn("NIT");
        model.addColumn("Codigo");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
        for (Empresa empresa : empresas) {
            model.addRow(new Object[]{
                empresa.getIdEmpresa(),
                empresa.getNombreEmpresa(),
                empresa.getNit(),
                empresa.getCodigo(),
                empresa.getDireccion() == null ? "" : empresa.getDireccion(),
                empresa.getTelefono() == null ? "" : empresa.getTelefono()
            });
        }
        tabla.setModel(model);
    }
    
    public void agreagaDatosTablaTrabajo(JTable tabla, List<Trabajo> trabajos) {
        DefaultTableModel model = new DefaultTableModel(){
             @Override
             public boolean isCellEditable(int rowIndex,int columnIndex){
                 return false;
             }
        };
        model.addColumn("ID");
        model.addColumn("ID Persona");
        model.addColumn("ID Empresa");
        model.addColumn("Fecha Inicial");
        model.addColumn("Fecha Final");
        model.addColumn("Puesto");
        model.addColumn("Mes Planilla");
        model.addColumn("Salario");
        for (Trabajo trabajo : trabajos) {
            model.addRow(new Object[]{
                trabajo.getIdTrabajo(),
                trabajo.getIdPersona(),
                trabajo.getIdEmpresa(),
                trabajo.getFechaIncial(),
                trabajo.getFechaFinal(),
                trabajo.getNombrePuesto() == null ? "" : trabajo.getNombrePuesto(),
                trabajo.getMesPlanilla() == null ? "" : trabajo.getMesPlanilla(),
                trabajo.getSalario()
            });
        }
        tabla.setModel(model);
    }
}
