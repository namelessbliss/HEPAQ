
package com.essaludapp.hepaq.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiagnosticoObj {

    @SerializedName("id_Diagnostico")
    @Expose
    public Integer idDiagnostico;
    @SerializedName("Codigo")
    @Expose
    public String codigo;
    @SerializedName("Descripcion")
    @Expose
    public String descripcion;
    @SerializedName("Tipo")
    @Expose
    public String tipo;
    @SerializedName("Caso")
    @Expose
    public String caso;
    @SerializedName("Alta")
    @Expose
    public String alta;
    @SerializedName("Estado")
    @Expose
    public String estado;
    @SerializedName("Fecha_Registro")
    @Expose
    public String fechaRegistro;
    @SerializedName("Fecha_Modificacion")
    @Expose
    public String fechaModificacion;
    @SerializedName("Empleado_registro")
    @Expose
    public String empleadoRegistro;
    @SerializedName("Empleado_modificacion")
    @Expose
    public String empleadoModificacion;
    @SerializedName("id_Personal")
    @Expose
    public Integer idPersonal;
    @SerializedName("Documento")
    @Expose
    public String documento;
    @SerializedName("Numero_ActoMed")
    @Expose
    public Integer numeroActoMed;

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public String getAlta() {
        return alta;
    }

    public void setAlta(String alta) {
        this.alta = alta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(String empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public String getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(String empleadoModificacion) {
        this.empleadoModificacion = empleadoModificacion;
    }

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getNumeroActoMed() {
        return numeroActoMed;
    }

    public void setNumeroActoMed(Integer numeroActoMed) {
        this.numeroActoMed = numeroActoMed;
    }

    public DiagnosticoObj() {
    }
}
