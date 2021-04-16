package com.essaludapp.hepaq.ui.vacuna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.common.Utils;
import com.essaludapp.hepaq.common.Vacuna;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.vacunas.ResponseConfirmarVacuna;
import com.essaludapp.hepaq.retrofit.response.vacunas.ResponseDosisVacuna;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAvisoVacunaRecyclerViewAdapter extends RecyclerView.Adapter<MyAvisoVacunaRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<ResponseDosisVacuna> mValues;
    private HEPAQService hepaqService;
    private HEPAQClient hepaqClient;
    private Utils utils;

    private String nombre, apellido, mensaje, documento;

    public MyAvisoVacunaRecyclerViewAdapter(Context context, List<ResponseDosisVacuna> items) {
        mValues = items;
        ctx = context;
        utils = new Utils();
        retrofitInit();
    }

    @Override
    public MyAvisoVacunaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacuna_aviso_list_item, parent, false);

        nombre = SharedPreferencesManager.getStringValue(Constants.PREF_NOMBRE);
        apellido = SharedPreferencesManager.getStringValue(Constants.PREF_APELLIDO);

        mensaje = String.format("Hola %1$s %2$s, su próxima vacuna se dará en la fecha:", nombre, apellido);
        documento = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO);
        return new MyAvisoVacunaRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mValues != null) { //Solo se carga si la lista existe

            holder.mItem = mValues.get(position);

            holder.tvMensajeVacuna.setText(mensaje);
            holder.tvTipo.setText("Descripción: " + holder.mItem.getDescripcion());

            String fechas = "";
            if (!holder.mItem.getFechaVacuna().isEmpty() && holder.mItem.getFechaVacuna() != null) {
                fechas += "Fecha 1: " + holder.mItem.getFechaVacuna();
            }
            if (!holder.mItem.getFechaVacuna2().isEmpty() && holder.mItem.getFechaVacuna2() != null) {
                fechas += "\nFecha 2: " + holder.mItem.getFechaVacuna();
            }
            if (!holder.mItem.getFechaVacuna3().isEmpty() && holder.mItem.getFechaVacuna3() != null) {
                fechas += "\nFecha 3: " + holder.mItem.getFechaVacuna();
            }
            holder.tvFecha.setText(fechas);

            holder.btnConfir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("¿Asistira a la cita?")
                            .setContentText(holder.mItem.getDescripcion() + "\nFecha: " + holder.mItem.getFechaVacuna())
                            .setConfirmText("Aceptar")
                            .showCancelButton(true)
                            .setCancelText("Cancelar")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    confirmarVacuna(position, holder.mItem.getIdDosisPaciente() + "", documento);
                                    sDialog.dismiss();
                                }
                            })
                            .show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvMensajeVacuna;
        public final TextView tvTipo;
        public final TextView tvFecha;
        public final Button btnConfir;
        public ResponseDosisVacuna mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvMensajeVacuna = (TextView) view.findViewById(R.id.tvMensajeVacuna);
            tvTipo = (TextView) view.findViewById(R.id.tvTipo);
            tvFecha = (TextView) view.findViewById(R.id.tvFecha);
            btnConfir = (Button) view.findViewById(R.id.btnConfir);
        }
    }

    private void retrofitInit() {
        hepaqClient = HEPAQClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void confirmarVacuna(int position, String idvacuna, String documento) {

        Call<ResponseConfirmarVacuna> call = hepaqService.confirmarDosisVacuna(idvacuna, documento);

        call.enqueue(new Callback<ResponseConfirmarVacuna>() {
            @Override
            public void onResponse(Call<ResponseConfirmarVacuna> call, Response<ResponseConfirmarVacuna> response) {
                if (response.isSuccessful()) {
                    if (response.body().isConfirmado()) {
                        mValues.remove(position);
                        new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("¡Registrado!")
                                .setContentText("La cita ha sido registrada en el historial.")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelText(null)
                                .setConfirmClickListener(null)
                                .show();
                        notifyDataSetChanged();
                    } else {
                        new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("ERROR")
                                .setContentText("No se pudo confirmar, contacte al administrador")
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseConfirmarVacuna> call, Throwable t) {
                if (t.getMessage().equals(Constants.NET_ERROR)) {
                    new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ERROR DE CONEXION")
                            .setContentText("Revise su conexion a internet")
                            .show();
                } else {
                    new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ERROR")
                            .setContentText("No se pudo confirmar, contacte al administrador")
                            .show();
                }
            }
        });
    }
}
