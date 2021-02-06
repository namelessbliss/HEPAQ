package com.example.prototipo.ui.resultados;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prototipo.R;
import com.example.prototipo.common.Ecografia;
import com.example.prototipo.common.Laboratorio;

import java.util.ArrayList;
import java.util.List;

public class LaboratorioFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyLaboratorioRecyclerViewAdapter adapter;
    private List<Laboratorio> lista;

    public LaboratorioFragment() {
        // Required empty public constructor
    }


    public static LaboratorioFragment newInstance(String param1, String param2) {
        LaboratorioFragment fragment = new LaboratorioFragment();
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
        View view = inflater.inflate(R.layout.fragment_laboratorio, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));


            retrofitInit();
            loadAtencionesData();
        }

        return view;
    }

    private void retrofitInit() {

    }

    private void loadAtencionesData() {

        lista = new ArrayList<>();
        lista.add(new Laboratorio("Accord", "2021-01-01", "xxxxxxxx", true));
        lista.add(new Laboratorio("Accord", "2021-01-02", "xxxxxxxx", true));
        lista.add(new Laboratorio("Accord", "2021-01-03", "xxxxxxxx", true));
        lista.add(new Laboratorio("Accord", "2021-01-04", "xxxxxxxx", true));
        lista.add(new Laboratorio("Accord", "2021-01-05", "xxxxxxxx", true));
        lista.add(new Laboratorio("Accord", "2021-01-06", "xxxxxxxx", true));

        adapter = new MyLaboratorioRecyclerViewAdapter(getActivity(), lista);
        recyclerView.setAdapter(adapter);
    }
}