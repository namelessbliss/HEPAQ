package com.example.prototipo.common;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import android.os.Environment;

import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
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
    /*    private Font slogan = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);
        private Font fHighText = new Font(Font.FontFamily.COURIER, 16, Font.BOLD, BaseColor.RED);*/
    PdfPCell[] cells;
    float spacing = 0.2f;

    public File createPdf() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page1 = pdfDocument.startPage(pageInfo);
        Canvas canvas = page1.getCanvas();

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(12f);
        paint.setFakeBoldText(true);
        canvas.drawText("ORDEN DE ATENCIÓN", pageInfo.getPageWidth() / 2, 30, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(12f);
        paint.setFakeBoldText(true);
        canvas.drawText("I. DATOS DEL PAAD", 10, 40, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(12f);
        paint.setFakeBoldText(true);
        canvas.drawText("I. DATOS DEL PAAD", 10, 40, paint);


        pdfDocument.finishPage(page1);

        File carpeta = new File(Environment.getExternalStorageDirectory().toString(), "HEPAQ_ESSALUD_APP");

        if (!carpeta.exists())
            carpeta.mkdirs();

        File file = new File(carpeta, "atencion.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDocument.close();

        return file;
    }

    public File createPDFAtencion(ResponseAtenciones atencion) {
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

            table1.addCell(validaString(atencion.getPacienteObj().getSedeObj().getRedAsistencial()));
            table1.addCell(validaString(atencion.getPacienteObj().getSedeObj().getCentroAsistencial()));
            table1.addCell(validaString(atencion.getFechaAtencion()));

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

            String apellidos = validaString(atencion.getPacienteObj().getApellidos());
            String[] splited = apellidos.split("\\s+");
            table2.addCell(validaString(splited[0]));
            table2.addCell(validaString(splited[1]));
            table2.addCell(validaString(atencion.getPacienteObj().getNombres()));

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

            table3.addCell(validaString(atencion.getPacienteObj().getTipo()));
            table3.addCell(validaString(atencion.getDocumento()));
            table3.addCell(validaString(calcularEdad(atencion.getPacienteObj().getFechaNacimiento())));
            table3.addCell(validaString(atencion.getPacienteObj().getnHistoriaClinica() + ""));
            table3.addCell(validaString(atencion.getPacienteObj().getTipoAsegurado()));
            table3.addCell(validaString(atencion.getNumeroActoMed() + ""));

            pt3.add(table3);
            document.add(pt3);

            Paragraph pt4 = new Paragraph("", fText);
            pt4.setSpacingBefore(spacing);
            pt4.setSpacingAfter(spacing);
            PdfPTable table4 = new PdfPTable(2);
            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

            table4.addCell("Anamesis:");
            table4.addCell(validaString(atencion.getAtencionMedicoObj().getAnamnesia()));
            table4.addCell("Examen Fisico:");
            table4.addCell(validaString(atencion.getAtencionMedicoObj().getExamenFisico()));
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

            table5.addCell(validaString(atencion.getAtencionMedicoObj().getPresionArterialHg()));
            table5.addCell(validaString(atencion.getAtencionMedicoObj().getPeso() + ""));
            table5.addCell(validaString(calcularEdad(atencion.getPacienteObj().getFechaNacimiento())));
            table5.addCell(validaString(atencion.getAtencionMedicoObj().getTalla() + ""));

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

            table6.addCell(validaString(atencion.getAtencionMedicoObj().getGestante()));
            table6.addCell(validaString(atencion.getAtencionMedicoObj().getFechaUltmaRegla()));
            table6.addCell(validaString(atencion.getAtencionMedicoObj().getTipoConsulta()));

            pt6.add(table6);
            document.add(pt6);

            String cabecera6[] = new String[]{"Diagnostico", "Tipo", "Cie"};
            Paragraph pt7 = new Paragraph("", fText);
            pt7.setSpacingBefore(spacing);
            PdfPTable table7 = new PdfPTable(new float[]{3, 1, 1});
            table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera6.length; i++) {
                table7.addCell(cabecera6[i]);
            }
            table7.setHeaderRows(1);

            for (int i = 0; i < atencion.getDiagnosticoObj().size(); i++) {
                table7.addCell(validaString(atencion.getDiagnosticoObj().get(i).getDescripcion()));
                table7.addCell(validaString(atencion.getDiagnosticoObj().get(i).getTipo()));
                table7.addCell(validaString(atencion.getDiagnosticoObj().get(i).getCodigo()));
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

            table8.addCell(validaString(atencion.getLaboratorioObj().getDetalleLaboratorioObj().getDescripcion()));
            table8.addCell(validaString(atencion.getLaboratorioObj().getFechaRegistro()));

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

            table11.addCell(validaString(atencion.getAtencionMedicoObj().getResultadoAtencion()));
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

            table13.addCell(validaString(atencion.getPersonalObj().getApellido() + " " + atencion.getPersonalObj().getNombre()));
            table13.addCell(validaString(atencion.getEspecialidad()));
            table13.addCell(validaString(atencion.getPersonalObj().getcMP()));
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

    private String validaString(String s) {
        if (s == null || s.isEmpty()) {
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
