package com.essaludapp.hepaq.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDosisVacuna {
    @SerializedName("id")
    @Expose
    private String dni;

    /**
     * No args constructor for use in serialization
     */
    public RequestDosisVacuna() {
    }


    public RequestDosisVacuna(String dni) {
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
