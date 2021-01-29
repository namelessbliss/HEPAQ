package com.example.prototipo.common;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {

    public File createPdf() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page1 = pdfDocument.startPage(pageInfo);

        Canvas canvas = page1.getCanvas();
        canvas.drawText("Hola mundo", 40, 50, paint);
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

}
