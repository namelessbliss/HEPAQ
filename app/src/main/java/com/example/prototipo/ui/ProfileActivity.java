package com.example.prototipo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.common.SharedPreferencesManager;

public class ProfileActivity extends AppCompatActivity {

    TextView tvNombre, tvEdad, tvDni, tvTelefono, tvCorreo, tvHistoriaClinica, tvAutogenerado;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Ocultar barra de toolbar
        //getSupportActionBar().hide();

        bindViews();
        getSharedPreferencesData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        tvNombre = findViewById(R.id.tvNombre);
        tvEdad = findViewById(R.id.tvEdad);
        tvDni = findViewById(R.id.tvDni);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvHistoriaClinica = findViewById(R.id.tvHistoriaClinica);
        tvAutogenerado = findViewById(R.id.tvAutogenerado);
    }

    private void getSharedPreferencesData() {
        tvNombre.setText(SharedPreferencesManager.getStringValue(Constants.PREF_NOMBRE) + " " +
                SharedPreferencesManager.getStringValue(Constants.PREF_APELLIDO));
        tvEdad.setText(SharedPreferencesManager.getStringValue(Constants.PREF_FECHA_NACIMIENTO));
        tvDni.setText(SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO));
        tvTelefono.setText(SharedPreferencesManager.getStringValue(Constants.PREF_TELEFONO));
        tvCorreo.setText(SharedPreferencesManager.getStringValue(Constants.PREF_CORREO));
        tvHistoriaClinica.setText(SharedPreferencesManager.getStringValue(Constants.PREF_N_HISTORIA_CLINICA));
        tvAutogenerado.setText(SharedPreferencesManager.getStringValue(Constants.PREF_AUTOGENERADO));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_in_top);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else finish();
    }
}