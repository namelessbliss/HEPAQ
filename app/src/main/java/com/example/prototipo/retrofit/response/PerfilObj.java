
package com.example.prototipo.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerfilObj {

    @SerializedName("Id_perfil")
    @Expose
    private Object idPerfil;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;

    public Object getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Object idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
