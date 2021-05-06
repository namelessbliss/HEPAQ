package com.essaludapp.hepaq.retrofit.request;

import com.essaludapp.hepaq.retrofit.response.tests.Dataform;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestRegistrarTest {

    @SerializedName("dataFrorm")
    @Expose
    public Dataform dataform;

    public RequestRegistrarTest() {
        dataform = new Dataform();
    }

    public RequestRegistrarTest(Dataform dataform) {
        this.dataform = dataform;
    }

    public Dataform getDataform() {
        return dataform;
    }

}


