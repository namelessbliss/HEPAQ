package com.example.prototipo.ui.atenciones;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.common.SharedPreferencesManager;
import com.example.prototipo.retrofit.HEPAQClient;
import com.example.prototipo.retrofit.HEPAQService;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;
import com.example.prototipo.ui.LoginActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtencionFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
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

    private void checkPermissions() {
        PermissionListener dialogOnDeniedPermissionListener = DialogOnDeniedPermissionListener.Builder.withContext(getActivity())
                .withTitle("Permiso Rechazado")
                .withMessage("El permiso es necesario para generar el PDF")
                .withButtonText("Aceptar")
                .withIcon(R.mipmap.ic_launcher_app)
                .build();


        permissionListener = new CompositePermissionListener((PermissionListener) ((Context) getActivity()), dialogOnDeniedPermissionListener);

        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(permissionListener)
                .check();
    }
}