package com.example.prototipo.ui.vacuna;

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
import com.example.prototipo.common.Vacuna;

import java.util.ArrayList;
import java.util.List;

public class HistorialVacunaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MyVacunaRecyclerViewAdapter adapter;
    private List<Vacuna> lista;

    public HistorialVacunaFragment() {
        // Required empty public constructor
    }

    public static HistorialVacunaFragment newInstance(String param1, String param2) {
        HistorialVacunaFragment fragment = new HistorialVacunaFragment();
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
        View view = inflater.inflate(R.layout.fragment_historial_vacuna, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    private void retrofitInit() {

    }

    private void loadAtencionesData() {

        lista = new ArrayList<>();
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-01", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-02", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-03", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-04", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-05", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-06", "xxxxxxxx", true));

        adapter = new MyVacunaRecyclerViewAdapter(getActivity(), lista);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
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