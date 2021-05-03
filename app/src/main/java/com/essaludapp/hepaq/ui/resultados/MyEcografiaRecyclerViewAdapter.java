package com.essaludapp.hepaq.ui.resultados;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Utils;
import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;
import com.essaludapp.hepaq.ui.PdfViewerActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MyEcografiaRecyclerViewAdapter extends RecyclerView.Adapter<MyEcografiaRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<ResponseAtenciones> mValues;

    private Utils utils;

    public MyEcografiaRecyclerViewAdapter(Context context, List<ResponseAtenciones> items) {
        mValues = items;
        ctx = context;
        utils = new Utils();
    }

    @Override
    public MyEcografiaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ecografia_list_item, parent, false);
        return new MyEcografiaRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyEcografiaRecyclerViewAdapter.ViewHolder holder, int position) {

        if (mValues != null) { //Solo se carga si la lista existe

            holder.mItem = mValues.get(position);

            holder.tvSituacion.setText(holder.mItem.getSituacion());
            //holder.tvTipo.setText(holder.mItem.getTipo());

            String[] fecha = utils.getDayOfWeek(holder.mItem.getEcografiaObj().getFechaCita());
            holder.tvFechaAtencion.setText(fecha[0]);
            holder.tvDiaSemana.setText(fecha[1]);

            holder.btnPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.btnPdf.setClickable(false);
                    Dexter.withActivity((Activity) ctx)
                            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            File file = utils.createPDFEcografia(ctx, mValues.get(position));
                                            Intent intent = new Intent(ctx, PdfViewerActivity.class);
                                            intent.putExtra("pdf", file.getAbsolutePath());
                                            holder.btnPdf.setClickable(true);
                                            ctx.startActivity(intent);
                                        }
                                    }).start();
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {
                                    // check for permanent denial of permission
                                    if (response.isPermanentlyDenied()) {
                                        new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Se Requiere Permiso")
                                                .setContentText("Esta aplicacion requiere el permiso para acceder a los archivos locales ")
                                                .setConfirmText("Aceptar")
                                                .showCancelButton(true)
                                                .setCancelText("Cancelar")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog.dismiss();
                                                        openSettings();
                                                    }
                                                })
                                                .show();
                                    }
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();
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
        public final TextView tvSituacion;
        public final TextView tvFechaAtencion;
        public final TextView tvDiaSemana;
        public final Button btnPdf;
        public ResponseAtenciones mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvSituacion = (TextView) view.findViewById(R.id.tvSituacion);
            tvFechaAtencion = (TextView) view.findViewById(R.id.tvFechaAtencion);
            tvDiaSemana = (TextView) view.findViewById(R.id.tvDiaSemana);
            btnPdf = (Button) view.findViewById(R.id.btnPdf);
        }

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", ctx.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult((Activity) ctx, intent, 101, null);
    }
}
