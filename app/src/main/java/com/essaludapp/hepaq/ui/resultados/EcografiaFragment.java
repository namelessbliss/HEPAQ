package com.essaludapp.hepaq.ui.resultados;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.Ecografia;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EcografiaFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MyEcografiaRecyclerViewAdapter adapter;
    private List<ResponseAtenciones> lista;

    private HEPAQService hepaqService;
    private HEPAQClient hepaqClient;

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

        return view;
    }

    private void retrofitInit() {
        hepaqClient = HEPAQClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void loadAtencionesData() {

        String documento = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO);

        Call<List<ResponseAtenciones>> call = hepaqService.getAllAtenciones(documento);

        call.enqueue(new Callback<List<ResponseAtenciones>>() {
            @Override
            public void onResponse(Call<List<ResponseAtenciones>> call, Response<List<ResponseAtenciones>> response) {
                if (response.isSuccessful()) {
                    lista = response.body();
                    adapter = new MyEcografiaRecyclerViewAdapter(getActivity(), lista);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<ResponseAtenciones>> call, Throwable t) {
                if (t.getMessage().equals(Constants.NET_ERROR)) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ERROR DE CONEXION")
                            .setContentText("Revise su conexion a internet")
                            .show();
                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ERROR")
                            .setContentText("Ocurrio un error")
                            .show();
                }
            }
        });
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