package com.example.prototipo.ui.vacuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.common.SharedPreferencesManager;
import com.example.prototipo.common.Vacuna;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;
import com.example.prototipo.ui.LoginActivity;
import com.example.prototipo.ui.atenciones.MyAtencionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VacunaActivity extends AppCompatActivity {

    Toolbar toolbar;
    private TextView tvMensaje, tvTipo, tvFecha;
    private Button btnConfirmarAsistencia;

    String fecha = "2021-03-01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        bindViews();
        loadMensaje();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadMensaje() {
        String nombre = SharedPreferencesManager.getStringValue(Constants.PREF_NOMBRE);
        String apellido = SharedPreferencesManager.getStringValue(Constants.PREF_APELLIDO);

        String mensaje = String.format("Hola %1$s %2$s", nombre, apellido);
        tvMensaje.setText(mensaje);

        String tipo = "xxxxxxxx";

        tvFecha.setText(String.format("Su proxima vacuna es el  %1$s", fecha));
        tvTipo.setText(String.format("El tipo de vacuna que corresponde es %1$s ", tipo));
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        tvMensaje = findViewById(R.id.tvMensajeVacuna);
        tvTipo = findViewById(R.id.tvTipo);
        tvFecha = findViewById(R.id.tvFecha);
        btnConfirmarAsistencia = findViewById(R.id.btnConfir);

        btnConfirmarAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(VacunaActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Asistencia")
                        .setContentText("¿Asistira a la cita de inmunización en la fecha de " + fecha)
                        .setConfirmText("Aceptar")
                        .showCancelButton(true)
                        .setCancelText("Cancelar")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("¡Registrado!")
                                        .setContentText("La cita ha sido registrada")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelText(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });
    }
}