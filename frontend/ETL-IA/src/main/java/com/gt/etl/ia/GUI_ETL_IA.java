/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.gt.etl.ia.gui.Principal;

/**
 *
 * @author bryan
 */
public class GUI_ETL_IA {
    
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        Principal principal = new Principal();
        principal.setVisible(true);
    }
}
