package com.essaludapp.hepaq.ui.acerca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.essaludapp.hepaq.R;

public class AcercaActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        //Ocultar barra de toolbar
        getSupportActionBar().hide();
        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
    }
}