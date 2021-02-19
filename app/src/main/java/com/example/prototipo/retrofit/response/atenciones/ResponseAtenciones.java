
package com.example.prototipo.retrofit.response.atenciones;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAtenciones {

    @SerializedName("Numero_ActoMed")
    @Expose
    public Integer numeroActoMed;
    @SerializedName("Areas_Hosp")
    @Expose
    public String areasHosp;
    @SerializedName("Profesional")
    @Expose
    public String profesional;
    @SerializedName("Turno")
    @Expose
    public String turno;
    @SerializedName("Especialidad")
    @Expose
    public String especialidad;
    @SerializedName("Act_Hospit")
    @Expose
    public String actHospit;
    @SerializedName("Act_Especifica")
    @Expose
    public String actEspecifica;
    @SerializedName("Fecha_Atencion")
    @Expose
    public String fechaAtencion;
    @SerializedName("Estado")
    @Expose
    public String estado;
    @SerializedName("Situacion")
    @Expose
    public String situacion;
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
    @SerializedName("Documento")
    @Expose
    public String documento;
    @SerializedName("pacienteObj")
    @Expose
    public PacienteObj pacienteObj;
    @SerializedName("id_Personal")
    @Expose
    public Integer idPersonal;
    @SerializedName("personalObj")
    @Expose
    public PersonalObj personalObj;
    @SerializedName("atencionMedicoObj")
    @Expose
    public AtencionMedicoObj atencionMedicoObj;
    @SerializedName("diagnosticoObj")
    @Expose
    public List<DiagnosticoObj> diagnosticoObj = null;
    @SerializedName("laboratorioObj")
    @Expose
    public LaboratorioObj laboratorioObj;

    public ResponseAtenciones() {
    }

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

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public String getTurno() {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Integer getIdPersonal() {
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

    public AtencionMedicoObj getAtencionMedicoObj() {
        return atencionMedicoObj;
    }

    public void setAtencionMedicoObj(AtencionMedicoObj atencionMedicoObj) {
        this.atencionMedicoObj = atencionMedicoObj;
    }

    public List<DiagnosticoObj> getDiagnosticoObj() {
        return diagnosticoObj;
    }

    public void setDiagnosticoObj(List<DiagnosticoObj> diagnosticoObj) {
        this.diagnosticoObj = diagnosticoObj;
    }

    public LaboratorioObj getLaboratorioObj() {
        return laboratorioObj;
    }

    public void setLaboratorioObj(LaboratorioObj laboratorioObj) {
        this.laboratorioObj = laboratorioObj;
    }
}
