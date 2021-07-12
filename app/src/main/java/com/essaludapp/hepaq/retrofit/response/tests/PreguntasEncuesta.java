package com.essaludapp.hepaq.retrofit.response.tests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreguntasEncuesta {
    @SerializedName("pregunta_1")
    @Expose
    private String pregunta_1;
    @SerializedName("pregunta_2")
    @Expose
    private String pregunta_2;
    @SerializedName("pregunta_3")
    @Expose
    private String pregunta_3;
    @SerializedName("pregunta_4")
    @Expose
    private String pregunta_4;
    @SerializedName("pregunta_5")
    @Expose
    private String pregunta_5;
    @SerializedName("pregunta_6")
    @Expose
    private String pregunta_6;
    @SerializedName("pregunta_7")
    @Expose
    private String pregunta_7;
    @SerializedName("pregunta_8")
    @Expose
    private String pregunta_8;
    @SerializedName("pregunta_9")
    @Expose
    private String pregunta_9;
    @SerializedName("pregunta_10")
    @Expose
    private String pregunta_10;
    @SerializedName("pregunta_11")
    @Expose
    private String pregunta_11;

    public PreguntasEncuesta(String pregunta_1, String pregunta_2, String pregunta_3, String pregunta_4, String pregunta_5, String pregunta_6, String pregunta_7, String pregunta_8, String pregunta_9, String pregunta_10, String pregunta_11) {
        this.pregunta_1 = pregunta_1;
        this.pregunta_2 = pregunta_2;
        this.pregunta_3 = pregunta_3;
        this.pregunta_4 = pregunta_4;
        this.pregunta_5 = pregunta_5;
        this.pregunta_6 = pregunta_6;
        this.pregunta_7 = pregunta_7;
        this.pregunta_8 = pregunta_8;
        this.pregunta_9 = pregunta_9;
        this.pregunta_10 = pregunta_10;
        this.pregunta_11 = pregunta_11;
    }

    public PreguntasEncuesta() {
    }

    public String getPregunta_1() {
        return pregunta_1;
    }

    public void setPregunta_1(String pregunta_1) {
        this.pregunta_1 = pregunta_1;
    }

    public String getPregunta_2() {
        return pregunta_2;
    }

    public void setPregunta_2(String pregunta_2) {
        this.pregunta_2 = pregunta_2;
    }

    public String getPregunta_3() {
        return pregunta_3;
    }

    public void setPregunta_3(String pregunta_3) {
        this.pregunta_3 = pregunta_3;
    }

    public String getPregunta_4() {
        return pregunta_4;
    }

    public void setPregunta_4(String pregunta_4) {
        this.pregunta_4 = pregunta_4;
    }

    public String getPregunta_5() {
        return pregunta_5;
    }

    public void setPregunta_5(String pregunta_5) {
        this.pregunta_5 = pregunta_5;
    }

    public String getPregunta_6() {
        return pregunta_6;
    }

    public void setPregunta_6(String pregunta_6) {
        this.pregunta_6 = pregunta_6;
    }

    public String getPregunta_7() {
        return pregunta_7;
    }

    public void setPregunta_7(String pregunta_7) {
        this.pregunta_7 = pregunta_7;
    }

    public String getPregunta_8() {
        return pregunta_8;
    }

    public void setPregunta_8(String pregunta_8) {
        this.pregunta_8 = pregunta_8;
    }

    public String getPregunta_9() {
        return pregunta_9;
    }

    public void setPregunta_9(String pregunta_9) {
        this.pregunta_9 = pregunta_9;
    }

    public String getPregunta_10() {
        return pregunta_10;
    }

    public void setPregunta_10(String pregunta_10) {
        this.pregunta_10 = pregunta_10;
    }

    public String getPregunta_11() {
        return pregunta_11;
    }

    public void setPregunta_11(String pregunta_11) {
        this.pregunta_11 = pregunta_11;
    }
}
