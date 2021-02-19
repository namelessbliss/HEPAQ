package com.example.prototipo.ui.resultados;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.prototipo.R;
import com.example.prototipo.common.Ecografia;
import com.example.prototipo.common.Vacuna;
import com.example.prototipo.ui.vacuna.MyVacunaRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class EcografiaFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MyEcografiaRecyclerViewAdapter adapter;
    private List<Ecografia> lista;


    public EcografiaFragment() {
        // Required empty public constructor
    }

    public static EcografiaFragment newInstance(String param1, String param2) {
        EcografiaFragment fragment = new EcografiaFragment();
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
        View view = inflater.inflate(R.layout.fragment_ecografia, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


            retrofitInit();
            loadAtencionesData();
        return view;
    }

    private void retrofitInit() {

    }

    private void loadAtencionesData() {

        lista = new ArrayList<>();
        lista.add(new Ecografia("Accord", "2021-01-01", "xxxxxxxx", true));
        lista.add(new Ecografia("Accord", "2021-01-02", "xxxxxxxx", true));
        lista.add(new Ecografia("Accord", "2021-01-03", "xxxxxxxx", true));
        lista.add(new Ecografia("Accord", "2021-01-04", "xxxxxxxx", true));
        lista.add(new Ecografia("Accord", "2021-01-05", "xxxxxxxx", true));
        lista.add(new Ecografia("Accord", "2021-01-06", "xxxxxxxx", true));

        adapter = new MyEcografiaRecyclerViewAdapter(getActivity(), lista);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        retrofitInit();
        loadAtencionesData();
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        lista = null;
        adapter = null;
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}