
package com.essaludapp.hepaq.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaLaboratorioCita {

    @SerializedName("id_lab_cita")
    @Expose
    private Integer idLabCita;
    @SerializedName("Resultado")
    @Expose
    private float resultado;
    @SerializedName("Documento")
    @Expose
    private String documento;
    @SerializedName("id_Personal")
    @Expose
    private int idPersonal;
    @SerializedName("Numero_ActoMed")
    @Expose
    private int numeroActoMed;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("Situacion")
    @Expose
    private String situacion;
    @SerializedName("fecha_registro")
    @Expose
    private String fechaRegistro;
    @SerializedName("fecha_modificacion")
    @Expose
    private String fechaModificacion;
    @SerializedName("empleado_registro")
    @Expose
    private String empleadoRegistro;
    @SerializedName("empleado_modificacion")
    @Expose
    private String empleadoModificacion;

    public Integer getIdLabCita() {
        return idLabCita;
    }

    public void setIdLabCita(Integer idLabCita) {
        this.idLabCita = idLabCita;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public Object getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getNumeroActoMed() {
        return numeroActoMed;
    }

    public void setNumeroActoMed(int numeroActoMed) {
        this.numeroActoMed = numeroActoMed;
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

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
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

}
