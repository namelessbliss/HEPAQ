package com.essaludapp.hepaq.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogin {
    @SerializedName("documento")
    @Expose
    private String dni;
    @SerializedName("fecha_naci")
    @Expose
    private String fecha_nacimiento;

    /**
     * No args constructor for use in serialization
     */
    public RequestLogin() {
    }

    /**
     * @param dni
     * @param fecha_nacimiento
     */
    public RequestLogin(String dni, String fecha_nacimiento) {
        super();
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
