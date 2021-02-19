package com.example.prototipo.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.common.SharedPreferencesManager;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;

import java.util.ArrayList;

public class VitalesFragment extends Fragment {

    private SpeedView frecCardiaca, frecResp, presSanguineaSys, presSanguineaDia;
    private ViewGroup frameCarga, graficos;
    private TextView tvCargando;
    boolean lastError = true;

    public VitalesFragment() {
        // Required empty public constructor
    }

    public static VitalesFragment newInstance(String param1, String param2) {
        VitalesFragment fragment = new VitalesFragment();
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
        View view = inflater.inflate(R.layout.fragment_vitales, container, false);

        bindView(view);
        setupGrafico();
        setupActions();
        return view;

    }

    private void bindView(View view) {
        frameCarga = view.findViewById(R.id.frameCarga);
        graficos = view.findViewById(R.id.graficos);
        tvCargando = view.findViewById(R.id.tvCargando);
        frecCardiaca = view.findViewById(R.id.frecCardiaca);
        frecResp = view.findViewById(R.id.frecResp);
        presSanguineaSys = view.findViewById(R.id.presSanguineaSys);
        presSanguineaDia = view.findViewById(R.id.presSanguineaDia);

    }

    private void setupGrafico() {
        frecCardiaca.setMinSpeed(0);
        frecCardiaca.setMaxSpeed(160);
        frecCardiaca.setUnit(" ");

        frecResp.setMinSpeed(0);
        frecResp.setMaxSpeed(37);
        frecResp.setUnit(" ");

        presSanguineaSys.setMinSpeed(70);
        presSanguineaSys.setMaxSpeed(160);
        presSanguineaSys.setUnit(" ");
        presSanguineaSys.speedTo(70, 0);

        presSanguineaDia.setMinSpeed(50);
        presSanguineaDia.setMaxSpeed(100);
        presSanguineaDia.setUnit(" ");
        presSanguineaDia.speedTo(50, 0);
    }

    private void setupActions() {
        frecCardiaca.clearSections();
        frecCardiaca.addSections(new Section(0f, .375f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.375f, .625f, getResources().getColor(R.color.speedview_verde), 40)
                , new Section(.625f, 1f, getResources().getColor(R.color.speedview_amarillo), 40));
        ArrayList<Float> ticks1 = new ArrayList<>();
        ticks1.add(.375f);
        ticks1.add(.625f);
        frecCardiaca.setTicks(ticks1);

        frecResp.clearSections();
        frecResp.addSections(new Section(0f, .32432432432f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.32432432432f, .67567567567f, getResources().getColor(R.color.speedview_verde), 40)
                , new Section(.67567567567f, 1f, getResources().getColor(R.color.speedview_amarillo), 40));
        ArrayList<Float> ticks2 = new ArrayList<>();
        ticks2.add(.32432432432f);
        ticks2.add(.67567567567f);
        frecResp.setTicks(ticks2);

        presSanguineaSys.clearSections();
        presSanguineaSys.addSections(new Section(0f, .22222222f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.22222222f, .55555555f, getResources().getColor(R.color.speedview_verde), 40)
                , new Section(.55555555f, .66666666f, getResources().getColor(R.color.speedview_verdeclaro), 40)
                , new Section(.66666666f, .77777778f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.77777778f, 1f, getResources().getColor(R.color.speedview_rojo), 40));
        ArrayList<Float> ticks3 = new ArrayList<>();
        ticks3.add(.22222222f);
        ticks3.add(.55555555f);
        ticks3.add(.66666666f);
        ticks3.add(.77777778f);
        presSanguineaSys.setTicks(ticks3);
        presSanguineaSys.setTextSize(14f);

        presSanguineaDia.clearSections();
        presSanguineaDia.addSections(new Section(0f, .2f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.2f, .4f, getResources().getColor(R.color.speedview_verde), 40)
                , new Section(.4f, .6f, getResources().getColor(R.color.speedview_verdeclaro), 40)
                , new Section(.6f, .8f, getResources().getColor(R.color.speedview_amarillo), 40)
                , new Section(.8f, 1f, getResources().getColor(R.color.speedview_rojo), 40));
        ArrayList<Float> ticks4 = new ArrayList<>();
        ticks4.add(.2f);
        ticks4.add(.4f);
        ticks4.add(.6f);
        ticks4.add(.8f);
        presSanguineaDia.setTicks(ticks4);
        presSanguineaDia.setTextSize(14f);
    }

    private void moverIndicador() {
        try {
            float frecCard = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.FREC_CARDIACA));
            float frecRes = Float.parseFloat(SharedPreferencesManager.getStringValue(Constants.FREC_RESP));

            frecCardiaca.speedTo(frecCard);

            frecResp.speedTo(frecRes);
        } catch (Exception e) {
            Log.e("VitalesFragment", e.getMessage());
        }


        //presSanguineaSys.speedTo(cantidad);

        //presSanguineaDia.speedTo(cantidad);
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