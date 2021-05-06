package com.essaludapp.hepaq.retrofit.response.tests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dataform {
    @SerializedName("Documento")
    @Expose
    private String Documento;
    @SerializedName("resultado")
    @Expose
    private int resultado;
    @SerializedName("id_tipo_test")
    @Expose
    private int id_tipo_test;

    public Dataform() {
    }

    public Dataform(String documento, int resultado, int id_tipo_test) {
        Documento = documento;
        this.resultado = resultado;
        this.id_tipo_test = id_tipo_test;
    }

    @Override
    public String toString() {
        return "{" +
                "\"Documento\":" + "\"" + Documento + "\"" +
                ", \"resultado\":" + resultado +
                ", \"id_tipo_test\":" + id_tipo_test +
                "}";
    }
}
