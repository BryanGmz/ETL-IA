/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia.database;

import com.gt.etl.ia.dto.Empresa;
import com.gt.etl.ia.dto.Persona;
import com.gt.etl.ia.dto.Trabajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class ManejadorDB {
    
    private Conexion conexion = Conexion.getConexion();
    private final String QUERY_PESONAS = "SELECT * FROM persona";
    private final String QUERY_TRABAJOS = "SELECT * FROM trabajo";
    private final String QUERY_EMPRESAS = "SELECT * FROM empresa";
    private static ManejadorDB manejadorDB;
    
    private ManejadorDB () {}
    
    public static ManejadorDB getInstacia() {
        if (manejadorDB == null) {
            manejadorDB = new ManejadorDB();
        } return manejadorDB;
    }
    
    public List<Persona> listaPersonas() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Persona> personas = new ArrayList<>();
        try (Connection connection = conexion.conectar()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PESONAS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personas.add(new Persona (
                        resultSet.getInt("id_persona"),
                        resultSet.getString("dpi"),
                        resultSet.getString("primer_nombre"),
                        resultSet.getString("primer_apellido"),
                        resultSet.getString("segundo_nombre"),
                        resultSet.getString("segundo_apellido"),
                        resultSet.getString("apellido_casada"),
                        resultSet.getString("nit"),
                        resultSet.getString("genero"),
                        resultSet.getString("orden_cedula"),
                        resultSet.getString("registro_cedula"),
                        resultSet.getString("direccion_residencia"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getDate("fecha_nacimiento")
                ));
            }
        } 
        conexion.desconectar();
        return personas;
    }
    
    public List<Trabajo> listaTrabajos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Trabajo> trabajos = new ArrayList<>();
        try (Connection connection = conexion.conectar()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TRABAJOS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trabajos.add(new Trabajo (
                        resultSet.getInt("id_trabajo"),
                        resultSet.getInt("id_persona_id"),
                        resultSet.getInt("id_empresa_id"),
                        resultSet.getDate("fecha_incial"),
                        resultSet.getDate("fecha_final"),
                        resultSet.getString("nombre_puesto"),
                        resultSet.getString("mes_planilla"),
                        resultSet.getFloat("salario")
                ));
            }
        }
        conexion.desconectar();
        return trabajos;
    }
    
    public List<Empresa> listaEmpresas() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Empresa> empresas = new ArrayList<>();
        try (Connection connection = conexion.conectar()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EMPRESAS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empresas.add(new Empresa (
                        resultSet.getInt("id_empresa"),
                        resultSet.getString("nombre_empresa"),
                        resultSet.getString("nit"),
                        resultSet.getString("codigo"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono")
                ));
            }
        }
        conexion.desconectar();
        return empresas;
    }
}
