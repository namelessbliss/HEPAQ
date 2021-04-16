package com.essaludapp.hepaq.retrofit.response.vacunas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseConfirmarVacuna {

    @SerializedName("confirmado")
    @Expose
    private boolean confirmado;

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

}