package com.example.prototipo.ui.resultados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prototipo.R;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;

import java.util.ArrayList;

public class ImcActivity extends AppCompatActivity implements View.OnKeyListener {

    Toolbar toolbar;
    private SpeedView speedometer;
    EditText etPeso, etTalla;
    TextView tvTipoPeso;

    float talla = 0;
    float peso = 0;
    float imc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        bindViews();
        setActions();
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        tvTipoPeso = findViewById(R.id.tvTipoPeso);
        etPeso = findViewById(R.id.etPeso);
        etTalla = findViewById(R.id.etTalla);
        speedometer = findViewById(R.id.estres);
        speedometer.setMinSpeed(0);
        speedometer.setMaxSpeed(40);
        speedometer.setWithTremble(false);
        speedometer.setUnit(" ");
    }

    private void setActions() {
        speedometer.clearSections();
        speedometer.addSections(new Section(0f, .125f, getResources().getColor(R.color.soft_blue), 70)
                , new Section(.125f, .25f, getResources().getColor(R.color.soft_blue), 70)
                , new Section(.25f, .4625f, getResources().getColor(R.color.soft_blue), 70)
                , new Section(.4625f, .5f, getResources().getColor(R.color.dark_lime_green), 70)
                , new Section(.5f, .625f, getResources().getColor(R.color.dark_lime_green), 70)
                , new Section(.625f, .75f, getResources().getColor(R.color.vivid_orange), 70)
                , new Section(.75f, .875f, getResources().getColor(R.color.vivid_orange), 70)
                , new Section(.875f, 1f, getResources().getColor(R.color.vivid_orange), 70));
        ArrayList<Float> ticks1 = new ArrayList<>();
        ticks1.add(.125f);
        ticks1.add(.25f);
        ticks1.add(.5f);
        ticks1.add(.625f);
        ticks1.add(.75f);
        ticks1.add(.875f);
        speedometer.setTicks(ticks1);

        etTalla.setOnKeyListener(this);
        etPeso.setOnKeyListener(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void moverIndicador(float cantidad) {
        speedometer.speedTo(cantidad);
        if (cantidad < 18.5) {
            tvTipoPeso.setText("Peso Bajo");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.soft_blue));
        } else if (cantidad >= 18.5 && cantidad <= 25) {
            tvTipoPeso.setText("Peso Normal");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.dark_lime_green));
        } else if (cantidad > 25 && cantidad <= 40) {
            tvTipoPeso.setText("Sobrepeso");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.vivid_orange));
        } else {
            tvTipoPeso.setText("");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.soft_blue));
        }
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event != null) {
            EditText editText = (EditText) v;
            int id = editText.getId();

            if (id == R.id.etPeso) {
                if (!editText.getText().toString().isEmpty()) {
                    peso = Float.parseFloat(editText.getText().toString());

                } else {
                    peso = 0;

                }
                imc = peso / (talla * talla);
            } else {
                if (!editText.getText().toString().isEmpty()) {
                    talla = (Float.parseFloat(editText.getText().toString())) / 100f;

                } else {
                    talla = 0;

                }
                imc = peso / (talla * talla);
            }
            if (imc > 0) {
                moverIndicador(imc);
            }
        }
        return false;
    }
}