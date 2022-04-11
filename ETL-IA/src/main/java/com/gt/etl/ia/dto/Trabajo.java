/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gt.etl.ia.dto;

import java.util.Date;

/**
 *
 * @author bryan
 */
public class Trabajo {
    
    private int idTrabajo;
    private int idPersona;
    private int idEmpresa;
    private Date fechaIncial;
    private Date fechaFinal;
    private String nombrePuesto;
    private String mesPlanilla;
    private float salario;

    public Trabajo(int idTrabajo, int idPersona, int idEmpresa, Date fechaIncial, Date fechaFinal, String nombrePuesto, String mesPlanilla, float salario) {
        this.idTrabajo = idTrabajo;
        this.idPersona = idPersona;
        this.idEmpresa = idEmpresa;
        this.fechaIncial = fechaIncial;
        this.fechaFinal = fechaFinal;
        this.nombrePuesto = nombrePuesto;
        this.mesPlanilla = mesPlanilla;
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getFechaIncial() {
        return fechaIncial;
    }

    public void setFechaIncial(Date fechaIncial) {
        this.fechaIncial = fechaIncial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getMesPlanilla() {
        return mesPlanilla;
    }

    public void setMesPlanilla(String mesPlanilla) {
        this.mesPlanilla = mesPlanilla;
    }
    
    
}
