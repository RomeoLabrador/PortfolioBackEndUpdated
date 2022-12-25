/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.romeo.Dto;

import javax.validation.constraints.NotBlank;


public class dtoHabilidad {
    @NotBlank
    private String nombreH;
    @NotBlank
    private String descripcionH;
    @NotBlank
    private int cantidad;
    
    //constructores

    public dtoHabilidad() {
    }

    public dtoHabilidad(String nombreH, String descripcionH, int cantidad) {
        this.nombreH = nombreH;
        this.descripcionH = descripcionH;
        this.cantidad = cantidad;
    }
    
    //getters and setters

    public String getNombreH() {
        return nombreH;
    }

    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }

    public String getDescripcionH() {
        return descripcionH;
    }

    public void setDescripcionH(String descripcion) {
        this.descripcionH = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
