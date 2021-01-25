
package com.example.prototipo.retrofit.response.atenciones;

import com.example.prototipo.retrofit.response.atenciones.PacienteObj;
import com.example.prototipo.retrofit.response.atenciones.PersonalObj;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAtenciones {

    @SerializedName("Numero_ActoMed")
    @Expose
    private Integer numeroActoMed;
    @SerializedName("Areas_Hosp")
    @Expose
    private String areasHosp;
    @SerializedName("Profesional")
    @Expose
    private String profesional;
    @SerializedName("Turno")
    @Expose
    private String turno;
    @SerializedName("Especialidad")
    @Expose
    private String especialidad;
    @SerializedName("Act_Hospit")
    @Expose
    private String actHospit;
    @SerializedName("Act_Especifica")
    @Expose
    private String actEspecifica;
    @SerializedName("Fecha_Atencion")
    @Expose
    private String fechaAtencion;
    @SerializedName("Estado")
    @Expose
    private String estado;
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
    @SerializedName("Documento")
    @Expose
    private String documento;
    @SerializedName("pacienteObj")
    @Expose
    private PacienteObj pacienteObj;
    @SerializedName("id_Personal")
    @Expose
    private Integer idPersonal;
    @SerializedName("personalObj")
    @Expose
    private PersonalObj personalObj;

    public Integer getNumeroActoMed() {
        return numeroActoMed;
    }

    public void setNumeroActoMed(Integer numeroActoMed) {
        this.numeroActoMed = numeroActoMed;
    }

    public String getAreasHosp() {
        return areasHosp;
    }

    public void setAreasHosp(String areasHosp) {
        this.areasHosp = areasHosp;
    }

    public Object getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public Object getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getActHospit() {
        return actHospit;
    }

    public void setActHospit(String actHospit) {
        this.actHospit = actHospit;
    }

    public String getActEspecifica() {
        return actEspecifica;
    }

    public void setActEspecifica(String actEspecifica) {
        this.actEspecifica = actEspecifica;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Object getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Object getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public Object getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Object getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Object getEmpleadoRegistro() {
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public PacienteObj getPacienteObj() {
        return pacienteObj;
    }

    public void setPacienteObj(PacienteObj pacienteObj) {
        this.pacienteObj = pacienteObj;
    }

    public Object getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public PersonalObj getPersonalObj() {
        return personalObj;
    }

    public void setPersonalObj(PersonalObj personalObj) {
        this.personalObj = personalObj;
    }

}
