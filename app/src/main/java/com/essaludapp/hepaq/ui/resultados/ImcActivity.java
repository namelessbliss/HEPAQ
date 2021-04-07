package com.essaludapp.hepaq.ui.resultados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.essaludapp.hepaq.R;
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
        speedometer.addSections(new Section(0f, .4625f, getResources().getColor(R.color.speedview_amarillo), 70)
                , new Section(.4625f, .625f, getResources().getColor(R.color.speedview_verde), 70)
                , new Section(.625f, .75f, getResources().getColor(R.color.md_yellow_800), 70)
                , new Section(.75f, .875f, getResources().getColor(R.color.speedview_rojo), 70)
                , new Section(.875f, 1f, getResources().getColor(R.color.speedview_rojo), 70));
        ArrayList<Float> ticks1 = new ArrayList<>();
        ticks1.add(0f);
        ticks1.add(.4625f);
        ticks1.add(.625f);
        ticks1.add(.75f);
        ticks1.add(.875f);
        ticks1.add(1f);
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
            tvTipoPeso.setText("Insuficiencia ponderal");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.speedview_amarillo));
        } else if (cantidad >= 18.5 && cantidad <= 25) {
            tvTipoPeso.setText("Intervalo Normal");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.speedview_verde));
        } else if (cantidad > 25 && cantidad < 30) {
            tvTipoPeso.setText("Sobrepeso");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.md_yellow_800));
        }else if (cantidad >= 30 && cantidad < 35) {
            tvTipoPeso.setText("Obesidad de clase I");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.speedview_rojo));
        }else if (cantidad >= 35 && cantidad < 40) {
            tvTipoPeso.setText("Obesidad de clase II");
            tvTipoPeso.setTextColor(getResources().getColor(R.color.vivid_orange));
        } else {
            tvTipoPeso.setText("Obesidad de clase III");
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