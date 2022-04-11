/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia.manejadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author bryan
 */
public class Archivo {
 
    private static Archivo instancia;
    
    private Archivo() {}
    
    public static Archivo getInstancia() {
        if (instancia == null) {
            instancia = new Archivo();
        } return instancia;
    }
    
    public String abrirArchivo(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        String enviar = null;
        FileReader file = new FileReader (archivo);
        try (BufferedReader buffer = new BufferedReader(file)) {
            while((cadena = buffer.readLine())!= null) {
                if (enviar == null) {
                    enviar = cadena;
                } else {
                    enviar = enviar + "\n" + cadena;
                }
            }
        }
        return enviar;
    }
    
}
