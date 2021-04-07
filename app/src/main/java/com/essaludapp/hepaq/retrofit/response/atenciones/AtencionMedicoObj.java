
package com.essaludapp.hepaq.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AtencionMedicoObj {

    @SerializedName("Anamnesia")
    @Expose
    public String anamnesia;
    @SerializedName("Examen_fisico")
    @Expose
    public String examenFisico;
    @SerializedName("Presion_arterial_mm")
    @Expose
    public String presionArterialMm;
    @SerializedName("Presion_arterial_hg")
    @Expose
    public String presionArterialHg;
    @SerializedName("Peso")
    @Expose
    public Double peso;
    @SerializedName("Talla")
    @Expose
    public Double talla;
    @SerializedName("IMC")
    @Expose
    public Double iMC;
    @SerializedName("Gestante")
    @Expose
    public String gestante;
    @SerializedName("Fecha_Ultma_regla")
    @Expose
    public String fechaUltmaRegla;
    @SerializedName("Tipo_Consulta")
    @Expose
    public String tipoConsulta;
    @SerializedName("Resultado_Atencion")
    @Expose
    public String resultadoAtencion;
    @SerializedName("Tipo_Contingencia")
    @Expose
    public String tipoContingencia;
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
    @SerializedName("tempCorporal")
    @Expose
    public Double tempCorporal;
    @SerializedName("FrecCardiaca")
    @Expose
    public Double frecCardiaca;
    @SerializedName("PreVenosa")
    @Expose
    public Double preVenosa;
    @SerializedName("FrecRespiratoria")
    @Expose
    public Double frecRespiratoria;
    @SerializedName("PerAbdominal")
    @Expose
    public Double perAbdominal;
    @SerializedName("planTrabajo")
    @Expose
    public String planTrabajo;
    @SerializedName("Observaciones")
    @Expose
    public String observaciones;
    @SerializedName("personalObj")
    @Expose
    public PersonalObj personalObj;
    @SerializedName("pacienteObj")
    @Expose
    public PacienteObj pacienteObj;


    public String getAnamnesia() {
        return anamnesia;
    }

    public void setAnamnesia(String anamnesia) {
        this.anamnesia = anamnesia;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    public String getPresionArterialMm() {
        return presionArterialMm;
    }

    public void setPresionArterialMm(String presionArterialMm) {
        this.presionArterialMm = presionArterialMm;
    }

    public String getPresionArterialHg() {
        return presionArterialHg;
    }

    public void setPresionArterialHg(String presionArterialHg) {
        this.presionArterialHg = presionArterialHg;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public Double getiMC() {
        return iMC;
    }

    public void setiMC(Double iMC) {
        this.iMC = iMC;
    }

    public String getGestante() {
        return gestante;
    }

    public void setGestante(String gestante) {
        this.gestante = gestante;
    }

    public String getFechaUltmaRegla() {
        return fechaUltmaRegla;
    }

    public void setFechaUltmaRegla(String fechaUltmaRegla) {
        this.fechaUltmaRegla = fechaUltmaRegla;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getResultadoAtencion() {
        return resultadoAtencion;
    }

    public void setResultadoAtencion(String resultadoAtencion) {
        this.resultadoAtencion = resultadoAtencion;
    }

    public String getTipoContingencia() {
        return tipoContingencia;
    }

    public void setTipoContingencia(String tipoContingencia) {
        this.tipoContingencia = tipoContingencia;
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

    public Double getTempCorporal() {
        return tempCorporal;
    }

    public void setTempCorporal(Double tempCorporal) {
        this.tempCorporal = tempCorporal;
    }

    public Double getFrecCardiaca() {
        return frecCardiaca;
    }

    public void setFrecCardiaca(Double frecCardiaca) {
        this.frecCardiaca = frecCardiaca;
    }

    public Double getPreVenosa() {
        return preVenosa;
    }

    public void setPreVenosa(Double preVenosa) {
        this.preVenosa = preVenosa;
    }

    public Double getFrecRespiratoria() {
        return frecRespiratoria;
    }

    public void setFrecRespiratoria(Double frecRespiratoria) {
        this.frecRespiratoria = frecRespiratoria;
    }

    public Double getPerAbdominal() {
        return perAbdominal;
    }

    public void setPerAbdominal(Double perAbdominal) {
        this.perAbdominal = perAbdominal;
    }

    public String getPlanTrabajo() {
        return planTrabajo;
    }

    public void setPlanTrabajo(String planTrabajo) {
        this.planTrabajo = planTrabajo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public PersonalObj getPersonalObj() {
        return personalObj;
    }

    public void setPersonalObj(PersonalObj personalObj) {
        this.personalObj = personalObj;
    }

    public PacienteObj getPacienteObj() {
        return pacienteObj;
    }

    public void setPacienteObj(PacienteObj pacienteObj) {
        this.pacienteObj = pacienteObj;
    }

    public AtencionMedicoObj() {
    }
}
