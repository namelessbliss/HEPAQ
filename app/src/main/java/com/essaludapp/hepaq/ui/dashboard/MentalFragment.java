package com.essaludapp.hepaq.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;

import java.util.ArrayList;

public class MentalFragment extends Fragment {

    private SpeedView estres, fantastico, cono;
    private ViewGroup frameCarga, graficos;
    private TextView tvCargando;
    boolean lastError = true;

    private Button btnTests;

    public MentalFragment() {
        // Required empty public constructor
    }

    public static MentalFragment newInstance(String param1, String param2) {
        MentalFragment fragment = new MentalFragment();
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
        View view = inflater.inflate(R.layout.fragment_mental, container, false);
        bindView(view);
        setupGrafico();
        setupActions();
        return view;
    }

    private void bindView(View view) {
        frameCarga = view.findViewById(R.id.frameCarga);
        graficos = view.findViewById(R.id.graficos);
        tvCargando = view.findViewById(R.id.tvCargando);
        estres = view.findViewById(R.id.estres);
        fantastico = view.findViewById(R.id.fantastico);
        cono = view.findViewById(R.id.cono);
        btnTests = view.findViewById(R.id.btnTests);
    }

    private void setupGrafico() {
        estres.setMinSpeed(0);
        estres.setMaxSpeed(56);
        estres.setUnit(" ");

        fantastico.setMinSpeed(0);
        fantastico.setMaxSpeed(120);
        fantastico.setUnit(" ");

        cono.setMinSpeed(0);
        cono.setMaxSpeed(20);
        cono.setUnit(" ");
    }

    private void setupActions() {
        fantastico.clearSections();
        fantastico.addSections(new Section(0f, .3916666667f, getResources().getColor(R.color.speedview_rojo), 40)
                , new Section(.3916666667f, .6f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.6f, .7f, getResources().getColor(R.color.speedview_verdeclaro), 40)
                , new Section(.7f, .85f, getResources().getColor(R.color.speedview_verdeclaro), 40)
                , new Section(.85f, 1f, getResources().getColor(R.color.speedview_verde), 40));
        ArrayList<Float> ticks1 = new ArrayList<>();
        ticks1.add(.3916666667f);
        ticks1.add(.6f);
        ticks1.add(.7f);
        ticks1.add(.85f);
        fantastico.setTicks(ticks1);

        estres.clearSections();
        estres.addSections(new Section(0f, .357142857f, getResources().getColor(R.color.speedview_verdeclaro), 40)
                , new Section(.357142857f, .67857142857f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.67857142857f, 1f, getResources().getColor(R.color.speedview_rojo), 40));
        ArrayList<Float> ticks2 = new ArrayList<>();
        ticks2.add(.357142857f);
        ticks2.add(.67857142857f);
        estres.setTicks(ticks2);

        cono.clearSections();
        cono.addSections(new Section(0f, .5f, getResources().getColor(R.color.speedview_rojo), 40)
                , new Section(.5f, 1f, getResources().getColor(R.color.speedview_verdeclaro), 40));
        ArrayList<Float> ticks3 = new ArrayList<>();
        ticks3.add(.5f);
        ticks3.add(1f);
        cono.setTicks(ticks2);
    }

    private void moverIndicador() {
        float estresIndice = 0, fantas = 0, con = 0;
        try {
            estresIndice = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.TEST_ESTRES_SCORE));
            fantas = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.TEST_FANTAS_SCORE));
        } catch (Exception e) {
            Log.e("MentalFragment", e.getMessage());
        }
        try {
            fantas = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.TEST_FANTAS_SCORE));
        } catch (Exception e) {
            Log.e("MentalFragment", e.getMessage());
        }

        try {
            con = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.TEST_CONO_SCORE));
        } catch (Exception e) {
            Log.e("MentalFragment", e.getMessage());
        }

        estres.speedTo(estresIndice);
        fantastico.speedTo(fantas);
        cono.speedTo(con);
    }

    public void visibilidad(boolean error) {
        lastError = error;
        if (lastError) {
            tvCargando.setText("No hay valores registrados, realice las pruebas");
            tvCargando.setTextColor(getActivity().getResources().getColor(R.color.red_error));
        } else {
            moverIndicador();
            frameCarga.setVisibility(View.INVISIBLE);
            graficos.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setupGrafico();
        setupActions();
        visibilidad(false);
    }
}