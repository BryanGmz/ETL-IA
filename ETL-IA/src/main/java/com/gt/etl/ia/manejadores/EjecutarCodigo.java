/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia.manejadores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author bryan
 */
public class EjecutarCodigo {
    
    private static EjecutarCodigo ejecutarCodigo;
    
    private EjecutarCodigo() {}
    
    public static EjecutarCodigo getInstancia() {
        if (ejecutarCodigo == null) {
            ejecutarCodigo = new EjecutarCodigo();
        } 
        return ejecutarCodigo;
    }
    
    public void codigoEjecutar(String ejecutar) throws IOException, InterruptedException {
        //Run macro on target 
        Process process = Runtime.getRuntime().exec(ejecutar); 
        //Read output 
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(process.getInputStream())); 
        String line, previous = null; 
        while ((line = br.readLine()) != null) 
            if (!line.equals(previous)) { 
                previous = line;
                out.append(line).append('\n');
                System.out.println(line); 
        } //Check result 
    }
    
    public static String runCommand(String[] command) {
        String result = "";
        try {
            Process ps = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result += line + "\n";
            }
            br.close();
            System.out.println("close ... ");
            ps.waitFor();
            System.out.println("wait over ...");

            return result;
        } catch (IOException ioe) {
                ioe.printStackTrace();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        System.out.println("child thread donn");
        return "";
}
}