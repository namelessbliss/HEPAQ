
package com.example.prototipo.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleLaboratorioObj {

    @SerializedName("codigo")
    @Expose
    public String codigo;
    @SerializedName("Descripcion")
    @Expose
    public String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DetalleLaboratorioObj() {
    }
}
