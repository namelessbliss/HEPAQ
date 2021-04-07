package com.essaludapp.hepaq.ui.vacuna;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VacunaAvisoFragment extends Fragment {

    private TextView tvMensaje, tvTipo, tvFecha;
    private Button btnConfirmarAsistencia;

    String fecha = "2021-03-01";

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

        bindViews(view);
        loadMensaje();
        return view;

    }

    private void loadMensaje() {
        String nombre = SharedPreferencesManager.getStringValue(Constants.PREF_NOMBRE);
        String apellido = SharedPreferencesManager.getStringValue(Constants.PREF_APELLIDO);

        String mensaje = String.format("Hola %1$s %2$s", nombre, apellido);
        tvMensaje.setText(mensaje);

        String tipo = "xxxxxxxx";

        tvFecha.setText(String.format("Su proxima vacuna es el  %1$s", fecha));
        tvTipo.setText(String.format("El tipo de vacuna que corresponde es %1$s ", tipo));
    }

    private void bindViews(View view) {
        tvMensaje = view.findViewById(R.id.tvMensajeVacuna);
        tvTipo = view.findViewById(R.id.tvTipo);
        tvFecha = view.findViewById(R.id.tvFecha);
        btnConfirmarAsistencia = view.findViewById(R.id.btnConfir);

        btnConfirmarAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Asistencia")
                        .setContentText("¿Asistira a la cita de inmunización en la fecha de " + fecha)
                        .setConfirmText("Aceptar")
                        .showCancelButton(true)
                        .setCancelText("Cancelar")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("¡Registrado!")
                                        .setContentText("La cita ha sido registrada")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelText(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });
    }


}