package com.essaludapp.hepaq.ui.resultados;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.ui.LoginActivity;
import com.essaludapp.hepaq.ui.resultados.tests.EncuestaActivity;
import com.essaludapp.hepaq.ui.resultados.tests.TestConocimientoActivity;
import com.essaludapp.hepaq.ui.resultados.tests.TestEstresActivity;
import com.essaludapp.hepaq.ui.resultados.tests.TestFantasticoActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SimulacionFragment extends Fragment implements View.OnClickListener {

    private Button btnIMC, btnTestEstres, btnTestFan, btnTestCono, btnEncuesta;

    public SimulacionFragment() {
        // Required empty public constructor
    }

    public static SimulacionFragment newInstance(String param1, String param2) {
        SimulacionFragment fragment = new SimulacionFragment();
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
        View view = inflater.inflate(R.layout.fragment_simulacion, container, false);

        bindViews(view);

        return view;
    }

    private void bindViews(View view) {
        btnIMC = view.findViewById(R.id.btnIMC);
        btnTestEstres = view.findViewById(R.id.btnTestEstres);
        btnTestFan = view.findViewById(R.id.btnTestFantastico);
        btnTestCono = view.findViewById(R.id.btnTestCono);
        btnEncuesta = view.findViewById(R.id.btnEncuesta);

        btnIMC.setOnClickListener(this);
        btnTestEstres.setOnClickListener(this);
        btnTestFan.setOnClickListener(this);
        btnTestCono.setOnClickListener(this);
        btnEncuesta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIMC:
                goToActivity(new ImcActivity());
                break;
            case R.id.btnTestEstres:
                goToActivity(new TestEstresActivity());
                break;
            case R.id.btnTestFantastico:
                goToActivity(new TestFantasticoActivity());
                break;
            case R.id.btnTestCono:
                goToActivity(new TestConocimientoActivity());
                break;
            case R.id.btnEncuesta: {
                boolean encuestaContestada = SharedPreferencesManager.getBooleanValue(Constants.ENCUESTA_CONTESTADA);
                if (encuestaContestada) {
                    try {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Encuesta registrada")
                                .setContentText("Usted ya a respondido la encuesta")
                                .show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(
                                getActivity(),
                                "Usted ya a respondido la encuesta",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                } else
                    goToActivity(new EncuestaActivity());
            }
            default:
                break;
        }
    }

    private void goToActivity(AppCompatActivity activity) {
        Intent intent = new Intent(getActivity(), activity.getClass());
        startActivity(intent);
    }
}