
package com.essaludapp.hepaq.retrofit.response.atenciones;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EcografiaObj {

    @SerializedName("id_Eco")
    @Expose
    private Integer idEco;
    @SerializedName("Fecha_cita")
    @Expose
    private String fechaCita;
    @SerializedName("Indicaciones")
    @Expose
    private String indicaciones;
    @SerializedName("Documento")
    @Expose
    private Object documento;
    @SerializedName("Numero_ActoMed")
    @Expose
    private Object numeroActoMed;
    @SerializedName("id_Personal")
    @Expose
    private Object idPersonal;
    @SerializedName("Situacion")
    @Expose
    private Object situacion;
    @SerializedName("Fecha_Registro")
    @Expose
    private Object fechaRegistro;
    @SerializedName("Fecha_Modificacion")
    @Expose
    private Object fechaModificacion;
    @SerializedName("Empleado_registro")
    @Expose
    private Object empleadoRegistro;
    @SerializedName("Empleado_modificacion")
    @Expose
    private Object empleadoModificacion;
    @SerializedName("listaEcografiaCitas")
    @Expose
    private List<ListaEcografiaCita> listaEcografiaCitas = null;
    @SerializedName("pacienteObj")
    @Expose
    private PacienteObj pacienteObj;
    @SerializedName("personalObj")
    @Expose
    private PersonalObj personalObj;

    public Integer getIdEco() {
        return idEco;
    }

    public void setIdEco(Integer idEco) {
        this.idEco = idEco;
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

    public void setDocumento(Object documento) {
        this.documento = documento;
    }

    public Object getNumeroActoMed() {
        return numeroActoMed;
    }

    public void setNumeroActoMed(Object numeroActoMed) {
        this.numeroActoMed = numeroActoMed;
    }

    public Object getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Object idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Object getSituacion() {
        return situacion;
    }

    public void setSituacion(Object situacion) {
        this.situacion = situacion;
    }

    public Object getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Object fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Object getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Object fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Object getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(Object empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public Object getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(Object empleadoModificacion) {
        this.empleadoModificacion = empleadoModificacion;
    }

    public List<ListaEcografiaCita> getListaEcografiaCitas() {
        return listaEcografiaCitas;
    }

    public void setListaEcografiaCitas(List<ListaEcografiaCita> listaEcografiaCitas) {
        this.listaEcografiaCitas = listaEcografiaCitas;
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

}
