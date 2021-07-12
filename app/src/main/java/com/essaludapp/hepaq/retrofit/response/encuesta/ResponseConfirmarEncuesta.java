package com.essaludapp.hepaq.retrofit.response.encuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseConfirmarEncuesta {

    @SerializedName("confirmacion")
    @Expose
    private boolean confirmado;

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
