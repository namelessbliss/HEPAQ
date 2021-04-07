package com.essaludapp.hepaq.common;

public class Vacuna {
    String nombre;
    String apellido;
    String fecha;
    String tipo;
    boolean estado;

    public Vacuna(String nombre, String apellido, String fecha, String tipo, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Vacuna() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
