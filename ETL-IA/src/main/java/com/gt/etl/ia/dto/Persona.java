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
public class Persona {
    
    private int idPersona;
    private String dpiPersona;
    private String primerNombre;
    private String primerApellido;
    private String segundoNombre;
    private String segundoApellido;
    private String apellidoCasada;
    private String nit;
    private String genero;
    private String ordenCedula;
    private String registroCedula;
    private String direccionResidencia;
    private String telefono;
    private String email;
    private Date fechaNacimiento;

    public Persona(int idPersona, String dpiPersona, String primerNombre, String primerApellido, String segundoNombre, String segundoApellido, String apellidoCasada, String nit, String genero, String ordenCedula, String registroCedula, String direccionResidencia, String telefono, String email, Date fechaNacimiento) {
        this.idPersona = idPersona;
        this.dpiPersona = dpiPersona;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.segundoNombre = segundoNombre;
        this.segundoApellido = segundoApellido;
        this.apellidoCasada = apellidoCasada;
        this.nit = nit;
        this.genero = genero;
        this.ordenCedula = ordenCedula;
        this.registroCedula = registroCedula;
        this.direccionResidencia = direccionResidencia;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDpiPersona() {
        return dpiPersona;
    }

    public void setDpiPersona(String dpiPersona) {
        this.dpiPersona = dpiPersona;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getOrdenCedula() {
        return ordenCedula;
    }

    public void setOrdenCedula(String ordenCedula) {
        this.ordenCedula = ordenCedula;
    }

    public String getRegistroCedula() {
        return registroCedula;
    }

    public void setRegistroCedula(String registroCedula) {
        this.registroCedula = registroCedula;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

   
}
