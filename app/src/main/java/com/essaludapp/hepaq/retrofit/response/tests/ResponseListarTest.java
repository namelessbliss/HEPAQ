package com.essaludapp.hepaq.retrofit.response.tests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseListarTest {
    @SerializedName("id_test")
    @Expose
    private Integer idTest;
    @SerializedName("resultado")
    @Expose
    private Integer resultado;
    @SerializedName("Documento")
    @Expose
    private String documento;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("fecha_ingreso")
    @Expose
    private String fechaIngreso;
    @SerializedName("id_tipo_test")
    @Expose
    private Integer idTipoTest;

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdTipoTest() {
        return idTipoTest;
    }

    public void setIdTipoTest(Integer idTipoTest) {
        this.idTipoTest = idTipoTest;
    }
}
