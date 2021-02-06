package com.example.prototipo.common;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import android.os.Environment;

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
    private Font fText = new Font(Font.FontFamily.COURIER, 14, Font.NORMAL);
    /*    private Font slogan = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);
        private Font fHighText = new Font(Font.FontFamily.COURIER, 16, Font.BOLD, BaseColor.RED);*/
    PdfPCell[] cells;
    int spacing = 1;

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

    public File createPDFAtencion() {
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
            Paragraph pt = new Paragraph();
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

            for (int i = 1; i < 2; i++) {
                table1.addCell("...");
                table1.addCell("...");
                table1.addCell("...");
                //cells[i].setPadding(10);
            }
            pt.add(table1);
            document.add(pt);

            Paragraph paragraph3 = new Paragraph("II. DATOS DE USUARIO", fTitle);
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            paragraph3.setPaddingTop(10f);
            paragraph3.add("");

            document.add(paragraph3);

            String cabecera2[] = new String[]{"Apellido Paterno", "Apellido Materno", "Nombres"};
            Paragraph pt2 = new Paragraph();
            pt2.setSpacingBefore(spacing);
            pt2.setSpacingAfter(spacing);
            PdfPTable table2 = new PdfPTable(cabecera2.length);
            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera2.length; i++) {
                table2.addCell(cabecera2[i]);
                //cells[i].setPadding(10);
            }
            table2.setHeaderRows(1);

            for (int i = 1; i < 2; i++) {
                table2.addCell("...");
                table2.addCell("...");
                table2.addCell("...");
                //cells[i].setPadding(10);
            }
            pt2.add(table2);
            document.add(pt2);

            Paragraph paragraph4 = new Paragraph("DOCUMENTO DE IDENTIDAD", fTitle);
            paragraph4.setAlignment(Element.ALIGN_CENTER);
            paragraph4.setPaddingTop(10f);

            document.add(paragraph4);

            String cabecera3[] = new String[]{"Tipo", "Número", "Edad", "H.C", "Tipo", "A. Medico"};
            Paragraph pt3 = new Paragraph();
            pt3.setSpacingBefore(spacing);
            pt3.setSpacingAfter(spacing);
            PdfPTable table3 = new PdfPTable(cabecera3.length);
            table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera3.length; i++) {
                table3.addCell(cabecera3[i]);
                //cells[i].setPadding(10);
            }
            table3.setHeaderRows(1);

            for (int i = 1; i < 2; i++) {
                table3.addCell("...");
                table3.addCell("...");
                table3.addCell("...");
                table3.addCell("...");
                table3.addCell("...");
                table3.addCell("...");
                //cells[i].setPadding(10);
            }
            pt3.add(table3);
            document.add(pt3);

            Paragraph pt4 = new Paragraph();
            pt4.setSpacingBefore(spacing);
            pt4.setSpacingAfter(spacing);
            PdfPTable table4 = new PdfPTable(2);
            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

            for (int i = 1; i < 2; i++) {
                table4.addCell("Anamesis:");
                table4.addCell("Lorem ipsum dolor sit amet, ");
                table4.addCell("Examen Fisico:");
                table4.addCell("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et ");
                //cells[i].setPadding(10);
            }
            pt4.add(table4);
            document.add(pt4);

            String cabecera4[] = new String[]{"Presión Arterial", "Peso(Kg)", "Edad", "Talla(mts)"};
            Paragraph pt5 = new Paragraph();
            pt5.setSpacingBefore(spacing);
            PdfPTable table5 = new PdfPTable(cabecera4.length);
            table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera4.length; i++) {
                table5.addCell(cabecera4[i]);
            }
            table5.setHeaderRows(1);

            for (int i = 0; i < cabecera4.length; i++) {
                table5.addCell("...");
            }
            pt5.add(table5);
            document.add(pt5);

            Paragraph paragraph5 = new Paragraph("III. ATENCIÓN MEDICA", fTitle);
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            paragraph5.setPaddingTop(10f);
            paragraph5.setSpacingAfter(spacing);

            document.add(paragraph5);

            String cabecera5[] = new String[]{"Gestante", "Fecha Ultima Regla", "Tipo Consulta"};
            Paragraph pt6 = new Paragraph();
            pt6.setSpacingBefore(spacing);
            PdfPTable table6 = new PdfPTable(cabecera5.length);
            table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera5.length; i++) {
                table6.addCell(cabecera5[i]);
            }
            table6.setHeaderRows(1);

            for (int i = 0; i < cabecera5.length; i++) {
                table6.addCell("...");
            }
            pt6.add(table6);
            document.add(pt6);

            String cabecera6[] = new String[]{"Diagnostico", "Tipo", "Cie"};
            Paragraph pt7 = new Paragraph();
            pt7.setSpacingBefore(spacing);
            PdfPTable table7 = new PdfPTable(new float[]{3, 1, 1});
            table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            for (int i = 0; i < cabecera6.length; i++) {
                table7.addCell(cabecera6[i]);
            }
            table6.setHeaderRows(1);

            for (int i = 0; i < cabecera6.length; i++) {
                table7.addCell("...");
            }
            pt7.add(table7);
            document.add(pt7);

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

}
