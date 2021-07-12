package com.essaludapp.hepaq.retrofit.response.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

    @SerializedName("documento")
    @Expose
    private String doc;
    @SerializedName("tipo_usuario")
    @Expose
    private String tipo_usuario;
    @SerializedName("grado_instruccion")
    @Expose
    private String grado_instruccion;
    @SerializedName("ocupacion")
    @Expose
    private String ocupacion;
    @SerializedName("preguntas")
    @Expose
    private PreguntasEncuesta preguntas;

    public Dataform() {
    }

    /**
     * Construcctor para registrar tests
     */
    public Dataform(String documento, int resultado, int id_tipo_test) {
        Documento = documento;
        this.resultado = resultado;
        this.id_tipo_test = id_tipo_test;
    }

    /**
     * Construcctor para registrar encuesta
     */
    public Dataform(String doc, String tipo_usuario, String grado_instruccion, String ocupacion, PreguntasEncuesta preguntas) {
        this.doc = doc;
        this.tipo_usuario = tipo_usuario;
        this.grado_instruccion = grado_instruccion;
        this.ocupacion = ocupacion;
        this.preguntas = preguntas;
    }

    @Override
    public String toString() {
        return "{" +
                "\"Documento\":" + "\"" + Documento + "\"" +
                ", \"resultado\":" + resultado +
                ", \"id_tipo_test\":" + id_tipo_test +
                "}";
    }

    public String toStringEnc() {

        return "{" +
                "\"documento\":" + "\"" + doc + "\"" +
                ", \"tipo_usuario\":" + "\"" + tipo_usuario + "\"" +
                ", \"grado_instruccion\":" + "\"" + grado_instruccion + "\"" +
                ", \"ocupacion\":" + "\"" + ocupacion + "\"" +
                ", \"preguntas\":" + preguntas +
                "}";
    }
}
