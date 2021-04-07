
package com.essaludapp.hepaq.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaboratorioObj {

    @SerializedName("id_Labo")
    @Expose
    private Integer idLabo;
    @SerializedName("Fecha_cita")
    @Expose
    private String fechaCita;
    @SerializedName("Indicaciones")
    @Expose
    private String indicaciones;
    @SerializedName("Documento")
    @Expose
    private String documento;
    @SerializedName("Numero_ActoMed")
    @Expose
    private Integer numeroActoMed;
    @SerializedName("id_Personal")
    @Expose
    private Integer idPersonal;
    @SerializedName("Situacion")
    @Expose
    private String situacion;
    @SerializedName("Fecha_Registro")
    @Expose
    private String fechaRegistro;
    @SerializedName("Fecha_Modificacion")
    @Expose
    private String fechaModificacion;
    @SerializedName("Empleado_registro")
    @Expose
    private String empleadoRegistro;
    @SerializedName("Empleado_modificacion")
    @Expose
    private String empleadoModificacion;
    @SerializedName("listaLaboratorioCitas")
    @Expose
    private List<ListaLaboratorioCita> listaLaboratorioCitas = null;
    @SerializedName("pacienteObj")
    @Expose
    private PacienteObj pacienteObj;
    @SerializedName("personalObj")
    @Expose
    private PersonalObj personalObj;

    public Integer getIdLabo() {
        return idLabo;
    }

    public void setIdLabo(Integer idLabo) {
        this.idLabo = idLabo;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Object getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Object getNumeroActoMed() {
        return numeroActoMed;
    }

    public void setNumeroActoMed(int numeroActoMed) {
        this.numeroActoMed = numeroActoMed;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
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

    public Object getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(String empleadoModificacion) {
        this.empleadoModificacion = empleadoModificacion;
    }

    public List<ListaLaboratorioCita> getListaLaboratorioCitas() {
        return listaLaboratorioCitas;
    }

    public void setListaLaboratorioCitas(List<ListaLaboratorioCita> listaLaboratorioCitas) {
        this.listaLaboratorioCitas = listaLaboratorioCitas;
    }

    public PacienteObj getPacienteObj() {
        return pacienteObj;
    }

    public void setPacienteObj(PacienteObj pacienteObj) {
        this.pacienteObj = pacienteObj;
    }

    public PersonalObj getPersonalObj() {
        return personalObj;
    }

    public void setPersonalObj(PersonalObj personalObj) {
        this.personalObj = personalObj;
    }

    public LaboratorioObj() {
    }
}
