package com.essaludapp.hepaq.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.essaludapp.hepaq.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewerActivity extends AppCompatActivity {

    Toolbar toolbar;
    PDFView pdfView;
    ImageView ivShare;
    File pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar);
        ivShare = findViewById(R.id.ivShare);

        //get the current intent
        Intent intent = getIntent();

        pdf = new File(intent.getStringExtra("pdf"));

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(pdf)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompartirPdf();
            }
        });
    }

    private void CompartirPdf() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");
        //forma antigua api<24
        //Uri uri = Uri.parse("file://" + pdf.getAbsolutePath());
        Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", pdf);
        //intent.setPackage("com.whatsapp");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_TEXT, crearMensaje());
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        try {
            startActivity(Intent.createChooser(intent, "Compartir Boleta"));
        } catch (Exception e) {
            Toast.makeText(PdfViewerActivity.this, "Error: El pdf no existe", Toast.LENGTH_SHORT).show();
        }
    }

    private String crearMensaje() {
        return "Le envio su boleta de compra, gracias por su preferencia.";
    }

}