package com.essaludapp.hepaq.retrofit.response.tests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegistrarTests {

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
