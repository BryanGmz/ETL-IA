/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bryan
 */
public class Conexion {
    
    
    protected final String USER = "bryan";
    protected final String PASSWORD = "Manager1";
    protected final String STRINGCONNECTION = "jdbc:postgresql://localhost:5432/repository_etl?useTimezone=true&serverTimezone=UTC";
    protected Connection connection = null;
    
    private static Conexion conexion;
    
    private Conexion() {}
    
    public static Conexion getConexion() {
        if (conexion == null) {
            conexion = new Conexion();
        } return conexion;
    }
    
    public Connection conectar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(STRINGCONNECTION, USER, PASSWORD);
            return connection;
    }
    
    public void desconectar() {
        connection = null;
    }
    
    
}
