package com.example.prototipo.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SedeObj {

    @SerializedName("Id_Sede")
    @Expose
    public Object idSede;
    @SerializedName("Red_Asistencial")
    @Expose
    public String redAsistencial;
    @SerializedName("Centro_Asistencial")
    @Expose
    public String centroAsistencial;

    public SedeObj() {
    }

    public Object getIdSede() {
        return idSede;
    }

    public void setIdSede(Object idSede) {
        this.idSede = idSede;
    }

    public String getRedAsistencial() {
        return redAsistencial;
    }

    public void setRedAsistencial(String redAsistencial) {
        this.redAsistencial = redAsistencial;
    }

    public String getCentroAsistencial() {
        return centroAsistencial;
    }

    public void setCentroAsistencial(String centroAsistencial) {
        this.centroAsistencial = centroAsistencial;
    }
}
