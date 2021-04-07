package com.essaludapp.hepaq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.essaludapp.hepaq.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewerActivity extends AppCompatActivity {

    Toolbar toolbar;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar);

        //get the current intent
        Intent intent = getIntent();

        File pdf = new File(intent.getStringExtra("pdf"));

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(pdf).load();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}