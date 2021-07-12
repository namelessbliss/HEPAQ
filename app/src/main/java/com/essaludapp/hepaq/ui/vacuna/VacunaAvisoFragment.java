package com.essaludapp.hepaq.ui.vacuna;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.vacunas.ResponseDosisVacuna;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VacunaAvisoFragment extends Fragment {

    private TextView tvMensaje, tvTipo, tvFecha;
    private Button btnConfirmarAsistencia;
    FrameLayout frameCarga;

    String fecha = "2021-03-01";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MyAvisoVacunaRecyclerViewAdapter adapter;
    private List<ResponseDosisVacuna> lista;

    private HEPAQService hepaqService;
    private HEPAQClient hepaqClient;

    public VacunaAvisoFragment() {
        // Required empty public constructor
    }

    public static VacunaAvisoFragment newInstance(String param1, String param2) {
        VacunaAvisoFragment fragment = new VacunaAvisoFragment();
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
        View view = inflater.inflate(R.layout.fragment_vacuna_aviso, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        frameCarga = view.findViewById(R.id.frameCarga);
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        retrofitInit();
        loadAtencionesData();
        return view;

    }

    private void retrofitInit() {
        hepaqClient = HEPAQClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void loadAtencionesData() {

        String documento = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO);

        Call<List<ResponseDosisVacuna>> call = hepaqService.getDosisNoConfirmados(documento);

        call.enqueue(new Callback<List<ResponseDosisVacuna>>() {
            @Override
            public void onResponse(Call<List<ResponseDosisVacuna>> call, Response<List<ResponseDosisVacuna>> response) {
                if (response.isSuccessful()) {
                    lista = response.body();
                    adapter = new MyAvisoVacunaRecyclerViewAdapter(getActivity(), lista);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    frameCarga.setVisibility(View.GONE);
                    if (lista.size() == 0){
                        frameCarga.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ResponseDosisVacuna>> call, Throwable t) {
                if (t.getMessage().equals(Constants.NET_ERROR)) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ERROR DE CONEXION")
                            .setContentText("Revise su conexion a internet")
                            .show();
                } else {
                    try {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("ERROR")
                                .setContentText("Ocurrio un error")
                                .show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
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