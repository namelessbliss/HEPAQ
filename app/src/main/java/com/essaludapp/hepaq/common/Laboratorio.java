package com.essaludapp.hepaq.common;

public class Laboratorio {
    String situacion;
    String fecha;
    String tipo;
    boolean estado;

    public Laboratorio(String situacion, String fecha, String tipo, boolean estado) {
        this.situacion = situacion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Laboratorio() {
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
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
