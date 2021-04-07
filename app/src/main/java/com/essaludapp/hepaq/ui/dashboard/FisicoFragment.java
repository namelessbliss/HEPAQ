package com.essaludapp.hepaq.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.common.Utils;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;

import java.util.ArrayList;

public class FisicoFragment extends Fragment {

    private SpeedView imcGrafico, perimetroAbdomi;
    private ViewGroup frameCarga, graficos;
    private TextView tvCargando, tvCircularEdad;
    boolean lastError = false;

    String edad = "";
    Utils utils = new Utils();

    public FisicoFragment() {
        // Required empty public constructor
    }

    public static FisicoFragment newInstance(String param1, String param2) {
        FisicoFragment fragment = new FisicoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fisico, container, false);
        bindView(view);
        setupGrafico();
        setupActions();
        return view;
    }

    private void bindView(View view) {
        frameCarga = view.findViewById(R.id.frameCarga);
        graficos = view.findViewById(R.id.graficos);
        tvCargando = view.findViewById(R.id.tvCargando);
        imcGrafico = view.findViewById(R.id.imcGrafico);
        perimetroAbdomi = view.findViewById(R.id.perimetroAbdomi);
        tvCircularEdad = view.findViewById(R.id.tvCicularEdad);

        edad = utils.calcularEdad(SharedPreferencesManager.getStringValue(Constants.PREF_FECHA_NACIMIENTO));
    }

    private void setupGrafico() {
        imcGrafico.setMinSpeed(0);
        imcGrafico.setMaxSpeed(40);
        imcGrafico.setUnit(" ");

        perimetroAbdomi.setMinSpeed(0);
        perimetroAbdomi.setMaxSpeed(150);
        perimetroAbdomi.setUnit(" ");
    }

    private void setupActions() {
        imcGrafico.clearSections();
        imcGrafico.addSections(new Section(0f, .4625f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.4625f, .625f, getResources().getColor(R.color.speedview_verde), 40)
                , new Section(.625f, .75f, getResources().getColor(R.color.md_yellow_800), 40)
                , new Section(.75f, .875f, getResources().getColor(R.color.speedview_rojo), 40)
                , new Section(.875f, 1f, getResources().getColor(R.color.speedview_rojo), 40));
        ArrayList<Float> ticks1 = new ArrayList<>();
        ticks1.add(0f);
        ticks1.add(.4625f);
        ticks1.add(.625f);
        ticks1.add(.75f);
        ticks1.add(.875f);
        ticks1.add(1f);
        imcGrafico.setTicks(ticks1);
        imcGrafico.setTextSize(20f);

        perimetroAbdomi.clearSections();
        perimetroAbdomi.addSections(new Section(0f, .41f, getResources().getColor(R.color.speedview_verde), 40)
                , new Section(.41f, .54666667f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.54666667f, 1f, getResources().getColor(R.color.speedview_rojo), 40));
        ArrayList<Float> ticks2 = new ArrayList<>();
        ticks2.add(.41f);
        ticks2.add(.54666667f);
        perimetroAbdomi.setTicks(ticks2);
        perimetroAbdomi.setTextSize(20f);
    }

    private void moverIndicador() {
        float imc = 0;
        float perimetro = 0;
        try {
            imc = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.IMC));
            perimetro = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.PERIMETRO_ABDOMINAL));


        } catch (Exception e) {
            Log.e("VitalesFragment", e.getMessage());
        }

        imcGrafico.speedTo(imc);
        perimetroAbdomi.speedTo(perimetro);
        tvCircularEdad.setText(edad);
    }

    public void visibilidad(boolean error) {
        lastError = error;
        if (lastError) {
            tvCargando.setText("NO HAY DATOS PARA MOSTRAR");
            tvCargando.setTextColor(getActivity().getResources().getColor(R.color.red_error));
        } else {
            moverIndicador();
            graficos.setVisibility(View.VISIBLE);
            frameCarga.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setupGrafico();
        setupActions();
        visibilidad(lastError);
    }
}