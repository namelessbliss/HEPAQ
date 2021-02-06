package com.example.prototipo.ui.vacuna;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Vacuna;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VacunaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VacunaFragment extends Fragment {


    private RecyclerView recyclerView;
    private MyVacunaRecyclerViewAdapter adapter;
    private List<Vacuna> lista;

    public VacunaFragment() {
        // Required empty public constructor
    }


    public static VacunaFragment newInstance(String param1, String param2) {
        VacunaFragment fragment = new VacunaFragment();
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
        View view = inflater.inflate(R.layout.fragment_vacuna_list, container, false);

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
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-01", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-02", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-03", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-04", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-05", "xxxxxxxx", true));
        lista.add(new Vacuna("Alberto", "Jimenez", "2021-01-06", "xxxxxxxx", true));

        adapter = new MyVacunaRecyclerViewAdapter(getActivity(), lista);
        recyclerView.setAdapter(adapter);
    }
}