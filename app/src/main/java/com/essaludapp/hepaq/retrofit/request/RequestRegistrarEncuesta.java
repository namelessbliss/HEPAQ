package com.essaludapp.hepaq.retrofit.request;

import com.essaludapp.hepaq.retrofit.response.tests.Dataform;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestRegistrarEncuesta {

    @SerializedName("dataForm")
    @Expose
    public Dataform dataform;

    public RequestRegistrarEncuesta() {
        dataform = new Dataform();
    }

    public RequestRegistrarEncuesta(Dataform dataform) {
        this.dataform = dataform;
    }

    public Dataform getDataform() {
        return dataform;
    }
}
