package com.example.prototipo.ui.resultados;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.prototipo.R;
import com.example.prototipo.ui.resultados.tests.TestEstresActivity;
import com.example.prototipo.ui.resultados.tests.TestFantasticoActivity;

public class SimulacionFragment extends Fragment implements View.OnClickListener {

    private Button btnIMC, btnTestEstres, btnTestFan;

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

        btnIMC.setOnClickListener(this);
        btnTestEstres.setOnClickListener(this);
        btnTestFan.setOnClickListener(this);
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
            default:
                break;
        }
    }

    private void goToActivity(AppCompatActivity activity) {
        Intent intent = new Intent(getActivity(), activity.getClass());
        startActivity(intent);
    }
}