package com.essaludapp.hepaq.common;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;
import com.essaludapp.hepaq.retrofit.response.vacunas.ResponseDosisVacuna;
import com.essaludapp.hepaq.ui.LoginActivity;
import com.essaludapp.hepaq.ui.ProfileActivity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private Font fTitle = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
    private Font fNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private Font fNormalBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font fFecha = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    /*    private Font slogan = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);
        private Font fHighText = new Font(Font.FontFamily.COURIER, 16, Font.BOLD, BaseColor.RED);*/
    PdfPCell[] cells;
    float spacing = 0.2f;
    int contadorEcografia = 0;

    public void cerrarSession(AppCompatActivity activity1, AppCompatActivity activity2) {
        try {
            if (!SharedPreferencesManager.getBooleanValue(Constants.PREF_RECORDAR)) {
                SharedPreferencesManager.setStringValue(Constants.PREF_DOCUMENTO, null);
                SharedPreferencesManager.setStringValue(Constants.PREF_FECHA_NACIMIENTO, null);
            }
            SharedPreferencesManager.setStringValue(Constants.PREF_NOMBRE, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_APELLIDO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_SEXO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_DIRECCION, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_TELEFONO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_CORREO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_TIPO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_TIPO_ASEGURADO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_N_HISTORIA_CLINICA, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_AUTOGENERADO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_ESTADO, null);
            SharedPreferencesManager.setStringValue(Constants.PREF_ID_SEDE, null);
            SharedPreferencesManager.setStringValue(Constants.TEST_FANTAS_SCORE, null);
            SharedPreferencesManager.setStringValue(Constants.TEST_ESTRES_SCORE, null);
            SharedPreferencesManager.setStringValue(Constants.TEST_CONO_SCORE, null);
            SharedPreferencesManager.setStringValue(Constants.FREC_CARDIACA, null);
            SharedPreferencesManager.setStringValue(Constants.FREC_RESP, null);
            SharedPreferencesManager.setStringValue(Constants.PRES_SANGUINEA_SYS, null);
            SharedPreferencesManager.setStringValue(Constants.PRES_SANGUINEA_DIA, null);
            SharedPreferencesManager.setStringValue(Constants.IMC, null);
            SharedPreferencesManager.setStringValue(Constants.PERIMETRO_ABDOMINAL, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(activity1, activity2.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity1.startActivity(intent);
    }

    public File createPDFVacuna(Context context, ResponseDosisVacuna vacuna) {
        //File carpeta = new File(context.getFilesDir().toString(), "HEPAQ_ESSALUD_APP");
        File carpeta = new File(Environment.getExternalStorageDirectory().toString(), "HEPAQ_ESSALUD_APP");

        if (!carpeta.exists())
            carpeta.mkdirs();

        File pdf = new File(carpeta, "vacuna.pdf");

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();

/*            Paragraph paragraph = null;
            try {
                paragraph = new Paragraph("CARTA CIRCULAR N°" + atencion.getLaboratorioObj().getIdLabo() + "- PRVR-RAAM-ESSALUD-2020", fTitle);
            } catch (Exception e) {
                e.printStackTrace();
                paragraph = new Paragraph("CARTA CIRCULAR N° 322 - PRVR-RAAM-ESSALUD-2020", fTitle);
            }
            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);*/

            Calendar currentTime = Calendar.getInstance();
            String fech = currentTime.get(Calendar.YEAR) + "-" + (currentTime.get(Calendar.MONTH) + 1) + "-" + currentTime.get(Calendar.DAY_OF_MONTH);
            String[] fechaActual = getMonthYear(fech);

            Paragraph paragraph1 = new Paragraph("Chachapoyas, " + fechaActual[0] + " de " + fechaActual[1] + " del " + fechaActual[2], fFecha);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragraph1);

            Paragraph paragraph7 = new Paragraph("DATOS DE VACUNA", fNormalBold);

            document.add(paragraph7);

            String cabecera1[] = new String[]{"Descripcion", " "};
            String examen[] = new String[]{" PRIMERA FECHA VACUNA", "SEGUNDA FECHA VACUNA", "TERCERA FECHA VACUNA", "DESCRIPCION", "CONFIRMACION", "FECHA DE ATENCION"};
            String resultados[] = new String[]{vacuna.getFechaVacuna(),
                    vacuna.getFechaVacuna2(), vacuna.getFechaVacuna3(), vacuna.getDescripcion(), vacuna.getConfirmacion(), vacuna.getFechaAtencion()};
            Paragraph pt = new Paragraph("", fText);
            pt.setSpacingBefore(spacing);
            pt.setSpacingAfter(spacing);
            PdfPTable table1 = new PdfPTable(cabecera1.length);
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera1.length; i++) {
                table1.addCell(cabecera1[i]);
                //cells[i].setPadding(10);
            }
            table1.setHeaderRows(1);

            for (int i = 0; i < examen.length; i++) {
                if (i == 7) {
                    table1.addCell(validaString(examen[i]));
                    table1.addCell(validaString(resultados[i]));
                    i++;
                } else {
                    table1.addCell(validaString(examen[i]));
                    table1.addCell(validaString(resultados[i]));
                }
            }


            pt.add(table1);
            document.add(pt);

            document.close();
        } catch (Exception e) {

        }

        return pdf;
    }

    public File createPDFLaboratorio(Context context, ResponseAtenciones atencion) {
        //File carpeta = new File(context.getFilesDir().toString(), "HEPAQ_ESSALUD_APP");
        File carpeta = new File(Environment.getExternalStorageDirectory().toString(), "HEPAQ_ESSALUD_APP");

        if (!carpeta.exists())
            carpeta.mkdirs();

        File pdf = new File(carpeta, "laboratorio.pdf");

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();

            Paragraph paragraph = null;
            try {
                paragraph = new Paragraph("CARTA CIRCULAR N°" + atencion.getLaboratorioObj().getIdLabo() + "- PRVR-RAAM-ESSALUD-2020", fTitle);
            } catch (Exception e) {
                e.printStackTrace();
                paragraph = new Paragraph("CARTA CIRCULAR N° 322 - PRVR-RAAM-ESSALUD-2020", fTitle);
            }
            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);

            Calendar currentTime = Calendar.getInstance();
            String fech = currentTime.get(Calendar.YEAR) + "-" + (currentTime.get(Calendar.MONTH) + 1) + "-" + currentTime.get(Calendar.DAY_OF_MONTH);
            String[] fechaActual = getMonthYear(fech);

            Paragraph paragraph1 = new Paragraph("Chachapoyas, " + fechaActual[0] + " de " + fechaActual[1] + " del " + fechaActual[2], fFecha);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragraph1);

            Paragraph paragraph2 = new Paragraph("SEÑOR(A):", fNormal);

            document.add(paragraph2);

            Paragraph paragraph3 = new Paragraph(atencion.getPacienteObj().getApellidos() + " " + atencion.getPacienteObj().getNombres(), fNormal);

            document.add(paragraph3);

            Paragraph paragraph4 = new Paragraph("Presente. -", fNormalBold);

            document.add(paragraph4);

            Paragraph paragraph5 = new Paragraph("ASUNTO: INFORMO RESULTADOS DE ATENCION DE SALUD", fNormal);

            document.add(paragraph5);

            String parr = "La presente lleva muestras de nuestro aprecio y a la vez nos permite compartir con usted la opinión de nuestro equipo de trabajo quienes han evaluado al detalle los registros con la información que usted mismo nos brindó junto a los resultados de los exámenes auxiliares llevados a cabo en el desarrollo de la Atención Preventiva realizada, siendo estas nuestras conclusiones:";
            Paragraph paragraph6 = new Paragraph(parr, fNormal);

            document.add(paragraph6);

            Paragraph paragraph7 = new Paragraph("RESULTADOS DE ANALISIS DE SANGRE", fNormalBold);

            document.add(paragraph7);

            float glucosa = 0, trigliceridos = 0, colesterol = 0, colesterolHdl = 0, colesterolLdl = 0, imc = 0, presionArtmm = 0, presionArthg = 0, hemoglobina = 0;

            try {
                for (int i = 0; i < atencion.getLaboratorioObj().getListaLaboratorioCitas().size(); i++) {
                    if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().startsWith("GLUCOSA") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("GLUCOSA"))
                        glucosa = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                    else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().startsWith("TRIGLICERIDOS") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("TRIGLICERIDOS"))
                        trigliceridos = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                    else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("COLESTEROL") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("COLESTEROL "))
                        colesterol = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                    else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("HDL") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("HDL "))
                        colesterolHdl = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                    else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("LDL") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("LDL "))
                        colesterolLdl = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                    else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().startsWith("HEMOGLOBINA") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("HEMOGLOBINA"))
                        hemoglobina = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                }

                imc = Float.parseFloat(atencion.getAtencionMedicoObj().getiMC() + "");
                presionArtmm = Float.parseFloat(atencion.getAtencionMedicoObj().getPresionArterialMm());
                presionArthg = Float.parseFloat(atencion.getAtencionMedicoObj().getPresionArterialHg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            String cabecera1[] = new String[]{"EXAMENES", "RESULTADOS", "VALORES NORMALES"};
            String valores[] = new String[]{"H: < 90    M: <80 CM", "< 100 MG/DL", "25-150 MG/DL", "135-200 MG/DL", "M:  >50   H:    >40 MG/DL", "< 130", "18.5- 24.9 ", "< 130/85 MM hg", "H= 12- 18 g/dl    M=11- 16 g/dl",};
            String examen[] = new String[]{"CIRCUNFERENCIA ABDOMINAL", "GLUCOSA", "TRIGLICERIDOS", "COLESTEROL", "COLESTEROL HDL", "COLESTEROL LDL", "INDICE DE MASA CORPORAL", "PRESIÓN ARTERIAL", "HEMOGLOBINA"};
            double resultados[] = new double[]{atencion.getAtencionMedicoObj().getPerAbdominal(),
                    glucosa, trigliceridos, colesterol, colesterolHdl, colesterolLdl, imc, presionArtmm, presionArthg, hemoglobina
            };
            Paragraph pt = new Paragraph("", fText);
            pt.setSpacingBefore(spacing);
            pt.setSpacingAfter(spacing);
            PdfPTable table1 = new PdfPTable(cabecera1.length);
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera1.length; i++) {
                table1.addCell(cabecera1[i]);
                //cells[i].setPadding(10);
            }
            table1.setHeaderRows(1);

            for (int i = 0; i < examen.length; i++) {
                if (i == 7) {
                    table1.addCell(validaString(examen[i]));
                    table1.addCell(validaString(resultados[i] + "/" + resultados[i + 1]));
                    table1.addCell(validaString(valores[i]));
                    i++;
                } else {
                    table1.addCell(validaString(examen[i]));
                    table1.addCell(validaString(resultados[i] + ""));
                    table1.addCell(validaString(valores[i]));
                }
            }


            pt.add(table1);
            document.add(pt);

            document.close();
        } catch (Exception e) {

        }
        return pdf;
    }

    public File createPDFEcografia(Context context, ResponseAtenciones atencion) {
        //File carpeta = new File(context.getFilesDir().toString(), "HEPAQ_ESSALUD_APP");
        File carpeta = new File(Environment.getExternalStorageDirectory().toString(), "HEPAQ_ESSALUD_APP");

        if (!carpeta.exists())
            carpeta.mkdirs();

        File pdf = new File(carpeta, "ecografia.pdf");

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();

            try {
                Calendar currentTime = Calendar.getInstance();
                String fech = currentTime.get(Calendar.YEAR) + "-" + (currentTime.get(Calendar.MONTH) + 1) + "-" + currentTime.get(Calendar.DAY_OF_MONTH);
                String[] fechaActual = getMonthYear(fech);

                Paragraph paragraph1 = new Paragraph("Chachapoyas, " + fechaActual[0] + " de " + fechaActual[1] + " del " + fechaActual[2], fFecha);
                paragraph1.setAlignment(Element.ALIGN_RIGHT);

                document.add(paragraph1);
            } catch (DocumentException e) {
                System.out.println(e.getMessage());
            }

            Paragraph paragraph = null;
            try {
                paragraph = new Paragraph("DIAGNÓSTICO(ECOGRAFÍA).", fTitle);
            } catch (Exception e) {
                e.printStackTrace();
                paragraph = new Paragraph("DIAGNÓSTICO(ECOGRAFÍA).", fTitle);
            }
            paragraph.setAlignment(Element.ALIGN_LEFT);

            document.add(paragraph);

            String cabecera[] = new String[]{"A. IMAGEN", "AUSENCIA DE ESTEATOSIS"};
            String cabecera1[] = new String[]{"B. IMAGEN", "ESTEATOSIS LEVE", "ESTEATOSIS MODERADO", "ESTEATOSIS GRAVE"};
            Paragraph pt0 = new Paragraph("", fText);
            pt0.setSpacingBefore(spacing);
            pt0.setSpacingAfter(spacing);
            PdfPTable table = new PdfPTable(new float[]{2.5f, 7.5f});
            PdfPTable table1 = new PdfPTable(new float[]{2.5f, 2.5f, 2.5f, 2.5f});
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            for (int i = 0; i < cabecera.length; i++) {
                table.addCell(new Phrase(cabecera[i], fTitle));
                //cells[i].setPadding(10);
            }
            table.setHeaderRows(1);

            try {
                table.addCell(validaString("Ecografía imagen significativa"));
                table.addCell(validaString(""));
            } catch (Exception e) {
                e.printStackTrace();
                table.addCell(validaString("Ecografía imagen significativa"));
                table.addCell(validaString(""));
            }

            for (int i = 0; i < cabecera1.length; i++) {
                table1.addCell(new Phrase(cabecera1[i], fTitle));
                //cells[i].setPadding(10);
            }
            table1.setHeaderRows(1);

            try {
                table1.addCell(validaString("Ecografía imagen significativa"));
                table1.addCell(validaString(""));
                table1.addCell(validaString(""));
                table1.addCell(validaString(""));
            } catch (Exception e) {
                e.printStackTrace();
                table1.addCell(validaString("Ecografía imagen significativa"));
                table1.addCell(validaString(""));
                table1.addCell(validaString(""));
                table1.addCell(validaString(""));
            }

            pt0.add(table);
            pt0.add(table1);
            document.add(pt0);

            Paragraph par2 = null;
            try {
                par2 = new Paragraph("Ecografia imagen significativa", fTitle);
            } catch (Exception e) {
                e.printStackTrace();
                par2 = new Paragraph("Ecografia imagen significativa", fTitle);
            }
            par2.setAlignment(Element.ALIGN_LEFT);

            List lista1 = new List();
            lista1.add("LEVE \nHipercogenicidad parénquima hepático (comparación con corteza renal y brazo).");
            lista1.add("MODERADO \nAtenuación: Perdida definición/ no visualización estracturas profundas( diafragma, vasos, segmentos posteriores hepáticos).");
            lista1.add("GRAVE \nMayor refracción, opacidad, infiltración grasa en el parénquima hepático.");

            Paragraph par3 = null;
            try {
                par3 = new Paragraph("Ecografia Sin imagen significativa", fTitle);
            } catch (Exception e) {
                e.printStackTrace();
                par3 = new Paragraph("Ecografia Sin imagen significativa", fTitle);
            }
            par3.setAlignment(Element.ALIGN_LEFT);

            List lista2 = new List();
            lista2.add("No se evidencia alteraciones según imagen.");

            document.add(par2);
            document.add(lista1);
            document.add(par3);
            document.add(lista2);

            Paragraph par4 = null;
            try {
                par4 = new Paragraph("DIAGNÓSTIVO FINAL:", fTitle);
            } catch (Exception e) {
                e.printStackTrace();
                par4 = new Paragraph("Ecografia Sin imagen significativa", fTitle);
            }
            par4.setAlignment(Element.ALIGN_LEFT);

            document.add(par4);

            String cabecera3[] = new String[]{"PARAMETROS", "ESTADO DE ESTEATOSIS"};
            Paragraph pt3 = new Paragraph("", fText);
            pt3.setSpacingBefore(spacing);
            pt3.setSpacingAfter(spacing);
            PdfPTable table3 = new PdfPTable(new float[]{7.5f, 2.5f});
            table3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera3.length; i++) {
                table3.addCell(new Phrase(cabecera3[i], fTitle));
                //cells[i].setPadding(10);
            }
            table.setHeaderRows(1);

            try {
                Phrase frase = new Phrase(validaString(generarListado(atencion)));
                PdfPCell celda = new PdfPCell(frase);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                table3.addCell(celda);
                if (contadorEcografia >= 2)
                    table3.addCell(validaString("ESTEATOSIS HEPATICA"));
                else
                    table3.addCell(validaString("NO ESTEATOSIS HEPATICA"));
            } catch (Exception e) {
                e.printStackTrace();
                table3.addCell(validaString("Ecografía imagen significativa"));
                table3.addCell(validaString(""));
            }

            pt3.add(table3);
            document.add(pt3);

            document.close();
        } catch (Exception e) {

        }
        return pdf;
    }

    private String generarListado(ResponseAtenciones atencion) {
        contadorEcografia = 0;
        String listado = "";
        try {
            if (atencion.getAtencionMedicoObj().getPerAbdominal() >= 90) {
                listado += "- Cicunferencia abdominal: " + atencion.getAtencionMedicoObj().getPerAbdominal() + " (> 90 cm)\n";
                contadorEcografia++;
            } else
                listado += "- Cicunferencia abdominal: " + atencion.getAtencionMedicoObj().getPerAbdominal() + " (< 90 cm)\n";
        } catch (Exception e) {
            listado += "- Cicunferencia abdominal: -\n";
        }

        try {
            if (Float.parseFloat(atencion.getAtencionMedicoObj().getPresionArterialMm()) >= 130 && Float.parseFloat(atencion.getAtencionMedicoObj().getPresionArterialHg()) >= 85) {
                contadorEcografia++;
                listado += "- Presión arterial: " + atencion.getAtencionMedicoObj().getPresionArterialMm() + "/" + atencion.getAtencionMedicoObj().getPresionArterialHg() + " (> 130/85 mmhg)\n";
            } else
                listado += "- Presión arterial: " + atencion.getAtencionMedicoObj().getPresionArterialMm() + "/" + atencion.getAtencionMedicoObj().getPresionArterialHg() + " (< 130/85 mmhg)\n";
        } catch (Exception e) {
            listado += "- Presión arterial: -\n";
        }

        float glucosa = 0, trigli = 0, hdl = 0;
        try {
            for (int i = 0; i < atencion.getLaboratorioObj().getListaLaboratorioCitas().size(); i++) {
                if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().startsWith("GLUCOSA") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("GLUCOSA"))
                    glucosa = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().startsWith("TRIGLICERIDOS") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("TRIGLICERIDOS"))
                    trigli = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("HDL") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("HDL "))
                    hdl = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
/*                else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("LDL") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("LDL "))
                    colesterolLdl = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();
                else if (atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().startsWith("HEMOGLOBINA") || atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getDescripcion().endsWith("HEMOGLOBINA"))
                    hemoglobina = atencion.getLaboratorioObj().getListaLaboratorioCitas().get(i).getResultado();*/
            }
            if (glucosa >= 100) {
                contadorEcografia++;
                listado += "- Glucosa: " + glucosa + " (> 100 mg/dl)\n";
            } else
                listado += "- Glucosa: " + glucosa + " (< 100 mg/dl)\n";
            if (trigli >= 0) {
                listado += "- Triglicéridos: " + trigli + "\n";
            } else
                listado += "- Triglicéridos: " + trigli + "\n";
            if (hdl <= 40) {
                contadorEcografia++;
                listado += "- HDL reducido: " + hdl + " (< 40 mg/dl)\n";
            } else
                listado += "- HDL reducido: " + hdl + " (> 40 mg/dl)\n";
        } catch (Exception e) {
            listado += "- Glucosa: -\n";
            listado += "- Triglicéridos: -\n";
            listado += "- HDL reducido: -\n";
        }


        return listado;
    }

    public File createPDFAtencion(Context context, ResponseAtenciones atencion) {
        //File carpeta = new File(context.getFilesDir().toString(), "HEPAQ_ESSALUD_APP");
        File carpeta = new File(Environment.getExternalStorageDirectory().toString(), "HEPAQ_ESSALUD_APP");

        if (!carpeta.exists())
            carpeta.mkdirs();

        File pdf = new File(carpeta, "atencion.pdf");

        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();

            Paragraph paragraph = new Paragraph("ORDEN DE ATENCIÓN", fTitle);
            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);

            Paragraph paragraph2 = new Paragraph("I. DATOS DEL PADD", fTitle);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            paragraph2.setPaddingTop(10f);
            paragraph2.add("");

            document.add(paragraph2);

            String cabecera1[] = new String[]{"Red Asistencial", "Centro Asistencial", "Fecha de atención"};
            Paragraph pt = new Paragraph("", fText);
            pt.setSpacingBefore(spacing);
            pt.setSpacingAfter(spacing);
            PdfPTable table1 = new PdfPTable(cabecera1.length);
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera1.length; i++) {
                table1.addCell(cabecera1[i]);
                //cells[i].setPadding(10);
            }
            table1.setHeaderRows(1);
            cells = table1.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBorderColor(BaseColor.WHITE);
                //cells[j].setPadding(10);
            }

            try {
                table1.addCell(validaString(atencion.getPacienteObj().getSedeObj().getRedAsistencial()));
            } catch (Exception e) {
                table1.addCell(" ");
            }
            try {
                table1.addCell(validaString(atencion.getPacienteObj().getSedeObj().getCentroAsistencial()));
            } catch (Exception e) {
                table1.addCell(" ");
            }
            try {
                table1.addCell(validaString(atencion.getFechaAtencion()));
            } catch (Exception e) {
                table1.addCell(" ");
            }

            pt.add(table1);
            document.add(pt);

            Paragraph paragraph3 = new Paragraph("II. DATOS DE USUARIO", fTitle);
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            paragraph3.setPaddingTop(10f);
            paragraph3.add("");

            document.add(paragraph3);

            String cabecera2[] = new String[]{"Apellido Paterno", "Apellido Materno", "Nombres"};
            Paragraph pt2 = new Paragraph("", fText);
            pt2.setSpacingBefore(spacing);
            pt2.setSpacingAfter(spacing);
            PdfPTable table2 = new PdfPTable(cabecera2.length);
            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera2.length; i++) {
                table2.addCell(cabecera2[i]);
                //cells[i].setPadding(10);
            }
            table2.setHeaderRows(1);

            try {
                String apellidos = validaString(atencion.getPacienteObj().getApellidos());
                String[] splited = apellidos.split("\\s+");
                table2.addCell(validaString(splited[0]));
                table2.addCell(validaString(splited[1]));
            } catch (Exception e) {
                table2.addCell(" ");
            }
            try {
                table2.addCell(validaString(atencion.getPacienteObj().getNombres()));
            } catch (Exception e) {
                table2.addCell(" ");
            }

            pt2.add(table2);
            document.add(pt2);

            Paragraph paragraph4 = new Paragraph("DOCUMENTO DE IDENTIDAD", fTitle);
            paragraph4.setAlignment(Element.ALIGN_CENTER);
            paragraph4.setPaddingTop(10f);

            document.add(paragraph4);

            String cabecera3[] = new String[]{"Tipo", "Número", "Edad", "H.C", "Tipo", "Acto Medico"};
            Paragraph pt3 = new Paragraph("", fText);
            pt3.setSpacingBefore(spacing);
            pt3.setSpacingAfter(spacing);
            PdfPTable table3 = new PdfPTable(cabecera3.length);
            table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera3.length; i++) {
                table3.addCell(cabecera3[i]);
                //cells[i].setPadding(10);
            }
            table3.setHeaderRows(1);

            try {
                table3.addCell(validaString(atencion.getPacienteObj().getTipo()));
            } catch (Exception e) {
                table3.addCell(" ");
            }
            try {
                table3.addCell(validaString(atencion.getDocumento()));
            } catch (Exception e) {
                table3.addCell(" ");
            }
            try {
                table3.addCell(validaString(calcularEdad(atencion.getPacienteObj().getFechaNacimiento())));
            } catch (Exception e) {
                table3.addCell(" ");
            }
            try {
                table3.addCell(validaString(atencion.getPacienteObj().getnHistoriaClinica() + ""));
            } catch (Exception e) {
                table3.addCell(" ");
            }
            try {
                table3.addCell(validaString(atencion.getPacienteObj().getTipoAsegurado()));
            } catch (Exception e) {
                table3.addCell(" ");
            }
            try {
                table3.addCell(validaString(atencion.getNumeroActoMed() + ""));
            } catch (Exception e) {
                table3.addCell(" ");
            }

            pt3.add(table3);
            document.add(pt3);

            Paragraph pt4 = new Paragraph("", fText);
            pt4.setSpacingBefore(spacing);
            pt4.setSpacingAfter(spacing);
            PdfPTable table4 = new PdfPTable(2);
            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

            table4.addCell("Anamesis:");
            try {
                table4.addCell(validaString(atencion.getAtencionMedicoObj().getAnamnesia()));
            } catch (Exception e) {
                table4.addCell(" ");
            }
            table4.addCell("Examen Fisico:");
            try {
                table4.addCell(validaString(atencion.getAtencionMedicoObj().getExamenFisico()));
            } catch (Exception e) {
                table4.addCell(" ");
            }
            //cells[i].setPadding(10);

            pt4.add(table4);
            document.add(pt4);

            String cabecera4[] = new String[]{"Presión Arterial", "Peso(Kg)", "Edad", "Talla(mts)"};
            Paragraph pt5 = new Paragraph("", fText);
            pt5.setSpacingBefore(spacing);
            PdfPTable table5 = new PdfPTable(cabecera4.length);
            table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera4.length; i++) {
                table5.addCell(cabecera4[i]);
            }
            table5.setHeaderRows(1);

            try {
                table5.addCell(validaString(atencion.getAtencionMedicoObj().getPresionArterialHg()));
            } catch (Exception e) {
                table5.addCell(" ");
            }
            try {
                table5.addCell(validaString(atencion.getAtencionMedicoObj().getPeso() + ""));
            } catch (Exception e) {
                table5.addCell(" ");
            }
            try {
                table5.addCell(validaString(calcularEdad(atencion.getPacienteObj().getFechaNacimiento())));
            } catch (Exception e) {
                table5.addCell(" ");
            }
            try {
                table5.addCell(validaString(atencion.getAtencionMedicoObj().getTalla() + ""));
            } catch (Exception e) {
                table5.addCell(" ");
            }

            pt5.add(table5);
            document.add(pt5);

            Paragraph paragraph5 = new Paragraph("III. ATENCIÓN MEDICA", fTitle);
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            paragraph5.setPaddingTop(10f);
            paragraph5.setSpacingAfter(spacing);

            document.add(paragraph5);

            String cabecera5[] = new String[]{"Gestante", "Fecha Ultima Regla", "Tipo Consulta"};
            Paragraph pt6 = new Paragraph("", fText);
            pt6.setSpacingBefore(spacing);
            PdfPTable table6 = new PdfPTable(cabecera5.length);
            table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera5.length; i++) {
                table6.addCell(cabecera5[i]);
            }
            table6.setHeaderRows(1);

            try {
                table6.addCell(validaString(atencion.getAtencionMedicoObj().getGestante()));
            } catch (Exception e) {
                table6.addCell(" ");
            }
            try {
                table6.addCell(validaString(atencion.getAtencionMedicoObj().getFechaUltmaRegla()));
            } catch (Exception e) {
                table6.addCell(" ");
            }
            try {
                table6.addCell(validaString(atencion.getAtencionMedicoObj().getTipoConsulta()));
            } catch (Exception e) {
                table6.addCell(" ");
            }

            pt6.add(table6);
            document.add(pt6);

            String cabecera6[] = new String[]{"Diagnostico", "Tipo"};
            Paragraph pt7 = new Paragraph("", fText);
            pt7.setSpacingBefore(spacing);
            PdfPTable table7 = new PdfPTable(new float[]{3, 1});
            table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera6.length; i++) {
                table7.addCell(cabecera6[i]);
            }
            table7.setHeaderRows(1);

            try {
                for (int i = 0; i < atencion.getDiagnosticoObj().size(); i++) {
                    table7.addCell(validaString(atencion.getDiagnosticoObj().get(i).getDescripcion()));
                    table7.addCell(validaString(atencion.getDiagnosticoObj().get(i).getTipo()));
                }
            } catch (Exception e) {
                table7.addCell(" ");
                table7.addCell(" ");
            }

            pt7.add(table7);
            document.add(pt7);

            String cabecera7[] = new String[]{"Exámenes de laboratorio", "Fecha"};
            Paragraph pt8 = new Paragraph("", fText);
            pt8.setSpacingBefore(spacing);
            PdfPTable table8 = new PdfPTable(new float[]{4, 1});
            table8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera7.length; i++) {
                table8.addCell(cabecera7[i]);
            }
            table8.setHeaderRows(1);

            String descrip = "";
            //TODO Revisar detalle
            for (int j = 0; j < atencion.getLaboratorioObj().getListaLaboratorioCitas().size(); j++) {
                if (j >= 1) {
                    descrip += ", " + atencion.getLaboratorioObj().getListaLaboratorioCitas().get(j).getDescripcion();
                } else {
                    descrip += "" + atencion.getLaboratorioObj().getListaLaboratorioCitas().get(j).getDescripcion();
                }
            }
            try {
                table8.addCell(validaString(descrip));
            } catch (Exception e) {
                table8.addCell(" ");
            }
            try {
                table8.addCell(validaString(atencion.getLaboratorioObj().getFechaCita() + ""));
            } catch (Exception e) {
                table8.addCell(" ");
            }

            pt8.add(table8);
            document.add(pt8);

            String cabecera8[] = new String[]{"Resultados de Eámenes de laboratorio", "Fecha"};
            Paragraph pt9 = new Paragraph("", fText);
            pt9.setSpacingBefore(spacing);
            PdfPTable table9 = new PdfPTable(new float[]{4, 1});
            table9.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera8.length; i++) {
                table9.addCell(cabecera8[i]);
            }
            table9.setHeaderRows(1);

            for (int i = 0; i < cabecera8.length; i++) {
                table8.addCell(validaString(""));
            }
            pt9.add(table9);
            document.add(pt9);

            String cabecera9[] = new String[]{"Procedimeinto", "Codigo"};
            Paragraph pt10 = new Paragraph("", fText);
            pt10.setSpacingBefore(spacing);
            PdfPTable table10 = new PdfPTable(new float[]{4, 1});
            table10.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera9.length; i++) {
                table10.addCell(cabecera9[i]);
            }
            table10.setHeaderRows(1);

            for (int i = 0; i < cabecera9.length; i++) {
                table10.addCell(validaString(""));
            }
            pt10.add(table10);
            document.add(pt10);

            String cabecera11[] = new String[]{"Resultado de Atención", "Si es Recita", "Si es Interconsulta"};
            Paragraph pt11 = new Paragraph("", fText);
            pt11.setSpacingBefore(spacing);
            pt11.setSpacingAfter(spacing);
            PdfPTable table11 = new PdfPTable(cabecera11.length);
            table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera11.length; i++) {
                table11.addCell(cabecera11[i]);
            }
            table11.setHeaderRows(1);
            cells = table11.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBorderColor(BaseColor.WHITE);
            }

            try {
                table11.addCell(validaString(atencion.getAtencionMedicoObj().getResultadoAtencion()));
            } catch (Exception e) {
                table11.addCell(" ");
            }
            table11.addCell(validaString(""));
            table11.addCell(validaString(""));

            pt11.add(table11);
            document.add(pt11);

            String cabecera12[] = new String[]{"CITT", "Días", "Trabajo Habitual", "Empleador/Razón Social"};
            Paragraph pt12 = new Paragraph("", fText);
            pt12.setSpacingBefore(spacing);
            pt12.setSpacingAfter(spacing);
            PdfPTable table12 = new PdfPTable(new float[]{1, 1, 2, 2});
            table12.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera12.length; i++) {
                table12.addCell(cabecera12[i]);
                //cells[i].setPadding(10);
            }
            table12.setHeaderRows(1);
            cells = table12.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBorderColor(BaseColor.WHITE);
                //cells[j].setPadding(10);
            }

            for (int i = 0; i < cabecera12.length; i++) {
                table12.addCell(validaString(""));
            }
            pt12.add(table12);
            document.add(pt12);

            Paragraph paragraph6 = new Paragraph("IV. RESPONSABLE DE ATENCIÓN", fTitle);
            paragraph6.setAlignment(Element.ALIGN_LEFT);
            paragraph6.setPaddingTop(10f);
            paragraph6.setSpacingAfter(spacing);
            document.add(paragraph6);

            String cabecera13[] = new String[]{"Apellidos y Nombres", "Especialidad", "CMP", "RNE"};
            Paragraph pt13 = new Paragraph("", fText);
            pt13.setSpacingBefore(spacing);
            PdfPTable table13 = new PdfPTable(new float[]{3, 2, 1, 1});
            table13.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera13.length; i++) {
                table13.addCell(cabecera13[i]);
            }
            table13.setHeaderRows(1);

            try {
                table13.addCell(validaString(atencion.getPersonalObj().getApellido() + " " + atencion.getPersonalObj().getNombre()));
            } catch (Exception e) {
                table13.addCell(" ");
            }
            try {
                table13.addCell(validaString(atencion.getEspecialidad()));
            } catch (Exception e) {
                table13.addCell(" ");
            }
            try {
                table13.addCell(validaString(atencion.getPersonalObj().getcMP()));
            } catch (Exception e) {
                table13.addCell(" ");
            }
            table13.addCell(validaString(""));

            pt13.add(table13);
            document.add(pt13);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdf;
    }

    public String[] getDayOfWeek(String fecha) {
        long startDate = 0l;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(fecha);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String weekdays[] = new DateFormatSymbols(Locale.ENGLISH).getWeekdays();
        Calendar c = Calendar.getInstance();
        Date date = new Date(startDate);
        c.setTime(date);

        String dia = "", mes = "", numero;

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        numero = day + "";


        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                dia = "DOMINGO";
                break;
            case Calendar.MONDAY:
                dia = "LUNES";
                break;
            case Calendar.TUESDAY:
                dia = "MARTES";
                break;
            case Calendar.WEDNESDAY:
                dia = "MIERCOLES";
                break;
            case Calendar.THURSDAY:
                dia = "JUEVES";
                break;
            case Calendar.FRIDAY:
                dia = "VIERNES";
                break;
            case Calendar.SATURDAY:
                dia = "SABADO";
                break;
        }
        switch (month) {
            case Calendar.JANUARY:
                mes = "ENE";
                break;
            case Calendar.FEBRUARY:
                mes = "FEB";
                break;
            case Calendar.MARCH:
                mes = "MAR";
                break;
            case Calendar.APRIL:
                mes = "ABR";
                break;
            case Calendar.MAY:
                mes = "MAY";
                break;
            case Calendar.JUNE:
                mes = "JUN";
                break;
            case Calendar.JULY:
                mes = "JUL";
                break;
            case Calendar.AUGUST:
                mes = "AGO";
                break;
            case Calendar.SEPTEMBER:
                mes = "SEP";
                break;
            case Calendar.OCTOBER:
                mes = "OCT";
                break;
            case Calendar.NOVEMBER:
                mes = "NOV";
                break;
            case Calendar.DECEMBER:
                mes = "DIC";
                break;
        }
        String[] fech = new String[]{mes + " " + numero, dia};
        return fech;
    }

    public String[] getMonthYear(String fecha) {
        long startDate = 0l;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(fecha);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String weekdays[] = new DateFormatSymbols(Locale.ENGLISH).getWeekdays();
        Calendar c = Calendar.getInstance();
        Date date = new Date(startDate);
        c.setTime(date);

        String dia = "", mes = "", numero;

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        numero = day + "";
        String ano = year + "";


        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                dia = "DOMINGO";
                break;
            case Calendar.MONDAY:
                dia = "LUNES";
                break;
            case Calendar.TUESDAY:
                dia = "MARTES";
                break;
            case Calendar.WEDNESDAY:
                dia = "MIERCOLES";
                break;
            case Calendar.THURSDAY:
                dia = "JUEVES";
                break;
            case Calendar.FRIDAY:
                dia = "VIERNES";
                break;
            case Calendar.SATURDAY:
                dia = "SABADO";
                break;
        }
        switch (month) {
            case Calendar.JANUARY:
                mes = "ENERO";
                break;
            case Calendar.FEBRUARY:
                mes = "FEBRERO";
                break;
            case Calendar.MARCH:
                mes = "MARZO";
                break;
            case Calendar.APRIL:
                mes = "ABRIL";
                break;
            case Calendar.MAY:
                mes = "MAYO";
                break;
            case Calendar.JUNE:
                mes = "JUNIO";
                break;
            case Calendar.JULY:
                mes = "JULIO";
                break;
            case Calendar.AUGUST:
                mes = "AGOSTO";
                break;
            case Calendar.SEPTEMBER:
                mes = "SEPTIEMBRE";
                break;
            case Calendar.OCTOBER:
                mes = "OCTUBRE";
                break;
            case Calendar.NOVEMBER:
                mes = "NOVIEMBRE";
                break;
            case Calendar.DECEMBER:
                mes = "DICIEMBRE";
                break;
        }
        String[] fech = new String[]{numero, mes, ano};
        return fech;
    }

    private String validaString(String s) {
        if (s == null || s.isEmpty() || s.equalsIgnoreCase("null")) {
            return "-";
        } else
            return s;
    }

    public String calcularEdad(String fecha) {
        long startDate = 0l;
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(fecha);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        Date date = new Date(startDate);
        c.setTime(date);

        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }


}
