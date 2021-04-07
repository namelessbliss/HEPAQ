package com.essaludapp.hepaq.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAtenciones {
    @SerializedName("documento")
    @Expose
    private String dni;

    public RequestAtenciones() {
    }

    public RequestAtenciones(String dni) {
        super();
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
