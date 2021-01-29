package com.example.prototipo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prototipo.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewerActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        //get the current intent
        Intent intent = getIntent();

        File pdf = new File(intent.getStringExtra("pdf"));

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(pdf).load();
    }
}