package com.essaludapp.hepaq.ui.atenciones;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtencionFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FrameLayout frameCarga;
    private MyAtencionRecyclerViewAdapter adapter;
    private List<ResponseAtenciones> atencionesList;

    private HEPAQService hepaqService;
    private HEPAQClient hepaqClient;

    private PermissionListener permissionListener;

    public AtencionFragment() {
    }

    public static AtencionFragment newInstance(int columnCount) {
        AtencionFragment fragment = new AtencionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atenciones_list, container, false);

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

        Call<List<ResponseAtenciones>> call = hepaqService.getAllAtenciones(documento);

        call.enqueue(new Callback<List<ResponseAtenciones>>() {
            @Override
            public void onResponse(Call<List<ResponseAtenciones>> call, Response<List<ResponseAtenciones>> response) {
                if (response.isSuccessful()) {
                    atencionesList = response.body();
                    adapter = new MyAtencionRecyclerViewAdapter(getActivity(), atencionesList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    frameCarga.setVisibility(View.GONE);
                    if (atencionesList.size() == 0){
                        frameCarga.setVisibility(View.VISIBLE);
                    }
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
}