package com.essaludapp.hepaq.retrofit.response.vacunas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDosisVacuna {

    @SerializedName("id_dosisPaciente")
    @Expose
    private int idDosisPaciente;
    @SerializedName("id_vacuna")
    @Expose
    private int idVacuna;
    @SerializedName("Documento")
    @Expose
    private String documento;
    @SerializedName("id_Personal")
    @Expose
    private int idPersonal;
    @SerializedName("fecha_vacuna")
    @Expose
    private String fechaVacuna;
    @SerializedName("fecha_vacuna2")
    @Expose
    private String fechaVacuna2;
    @SerializedName("fecha_vacuna3")
    @Expose
    private String fechaVacuna3;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("max_dosis")
    @Expose
    private int maxDosis;
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
    @SerializedName("confirmacion")
    @Expose
    private String confirmacion;
    @SerializedName("fecha_atencion")
    @Expose
    private String fechaAtencion;

    public int getIdDosisPaciente() {
        return idDosisPaciente;
    }

    public void setIdDosisPaciente(int idDosisPaciente) {
        this.idDosisPaciente = idDosisPaciente;
    }

    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
    }

    public String getDocumento() {
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

    public String getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(String fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }

    public String getFechaVacuna2() {
        return fechaVacuna2;
    }

    public void setFechaVacuna2(String fechaVacuna2) {
        this.fechaVacuna2 = fechaVacuna2;
    }

    public String getFechaVacuna3() {
        return fechaVacuna3;
    }

    public void setFechaVacuna3(String fechaVacuna3) {
        this.fechaVacuna3 = fechaVacuna3;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMaxDosis() {
        return maxDosis;
    }

    public void setMaxDosis(int maxDosis) {
        this.maxDosis = maxDosis;
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

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

}
