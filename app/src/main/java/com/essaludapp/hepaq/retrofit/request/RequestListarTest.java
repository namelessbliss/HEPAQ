package com.essaludapp.hepaq.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestListarTest {

    @SerializedName("dni")
    @Expose
    private String documento;
    @SerializedName("tipo")
    @Expose
    private Integer idTipoTest;

    public RequestListarTest() {
    }

    public RequestListarTest(String documento, Integer idTipoTest) {
        this.documento = documento;
        this.idTipoTest = idTipoTest;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getIdTipoTest() {
        return idTipoTest;
    }

    public void setIdTipoTest(Integer idTipoTest) {
        this.idTipoTest = idTipoTest;
    }
}
