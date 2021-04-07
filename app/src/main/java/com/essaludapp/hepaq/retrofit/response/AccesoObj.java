
package com.essaludapp.hepaq.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccesoObj {

    @SerializedName("idAcceso")
    @Expose
    private Object idAcceso;
    @SerializedName("mod1_Adm")
    @Expose
    private Integer mod1Adm;
    @SerializedName("mod2_CExt")
    @Expose
    private Integer mod2CExt;
    @SerializedName("mod3_Regi")
    @Expose
    private Integer mod3Regi;
    @SerializedName("mod4_Repor")
    @Expose
    private Integer mod4Repor;
    @SerializedName("mod5_Mant")
    @Expose
    private Integer mod5Mant;
    @SerializedName("mod6_Noso")
    @Expose
    private Integer mod6Noso;
    @SerializedName("Id_perfil")
    @Expose
    private Object idPerfil;

    public Object getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Object idAcceso) {
        this.idAcceso = idAcceso;
    }

    public Integer getMod1Adm() {
        return mod1Adm;
    }

    public void setMod1Adm(Integer mod1Adm) {
        this.mod1Adm = mod1Adm;
    }

    public Integer getMod2CExt() {
        return mod2CExt;
    }

    public void setMod2CExt(Integer mod2CExt) {
        this.mod2CExt = mod2CExt;
    }

    public Integer getMod3Regi() {
        return mod3Regi;
    }

    public void setMod3Regi(Integer mod3Regi) {
        this.mod3Regi = mod3Regi;
    }

    public Integer getMod4Repor() {
        return mod4Repor;
    }

    public void setMod4Repor(Integer mod4Repor) {
        this.mod4Repor = mod4Repor;
    }

    public Integer getMod5Mant() {
        return mod5Mant;
    }

    public void setMod5Mant(Integer mod5Mant) {
        this.mod5Mant = mod5Mant;
    }

    public Integer getMod6Noso() {
        return mod6Noso;
    }

    public void setMod6Noso(Integer mod6Noso) {
        this.mod6Noso = mod6Noso;
    }

    public Object getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Object idPerfil) {
        this.idPerfil = idPerfil;
    }

}
