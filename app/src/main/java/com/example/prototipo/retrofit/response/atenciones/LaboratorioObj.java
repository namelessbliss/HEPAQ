
package com.example.prototipo.retrofit.response.atenciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaboratorioObj {

    @SerializedName("id_Labo")
    @Expose
    public Integer idLabo;
    @SerializedName("Fecha_registro")
    @Expose
    public String fechaRegistro;
    @SerializedName("Indicaciones")
    @Expose
    public String indicaciones;
    @SerializedName("codigos")
    @Expose
    public String codigos;
    @SerializedName("Documento")
    @Expose
    public String documento;
    @SerializedName("Numero_ActoMed")
    @Expose
    public Integer numeroActoMed;
    @SerializedName("id_Personal")
    @Expose
    public Integer idPersonal;
    @SerializedName("detalleLaboratorioObj")
    @Expose
    public DetalleLaboratorioObj detalleLaboratorioObj;

    public Integer getIdLabo() {
        return idLabo;
    }

    public void setIdLabo(Integer idLabo) {
        this.idLabo = idLabo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getCodigos() {
        return codigos;
    }

    public void setCodigos(String codigos) {
        this.codigos = codigos;
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

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public DetalleLaboratorioObj getDetalleLaboratorioObj() {
        return detalleLaboratorioObj;
    }

    public void setDetalleLaboratorioObj(DetalleLaboratorioObj detalleLaboratorioObj) {
        this.detalleLaboratorioObj = detalleLaboratorioObj;
    }

    public LaboratorioObj() {
    }
}
