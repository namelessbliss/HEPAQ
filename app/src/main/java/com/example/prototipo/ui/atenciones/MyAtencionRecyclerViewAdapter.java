package com.example.prototipo.ui.atenciones;

import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;
import com.example.prototipo.common.Utils;
import com.example.prototipo.ui.PdfViewerActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.List;


public class MyAtencionRecyclerViewAdapter extends RecyclerView.Adapter<MyAtencionRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<ResponseAtenciones> mValues;

    private Utils utils;

    public MyAtencionRecyclerViewAdapter(Context context, List<ResponseAtenciones> items) {
        mValues = items;
        ctx = context;
        utils = new Utils();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_atenciones_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (mValues != null) { //Solo se carga si la lista existe

            holder.mItem = mValues.get(position);

            //holder.tvNumeroAtencion.setText(position + 1);
            String nombreCompleto = holder.mItem.getPersonalObj().getNombre() + " " + holder.mItem.getPersonalObj().getApellido();
            holder.tvMedico.setText(nombreCompleto);

            String[] fecha = utils.getDayOfWeek(holder.mItem.getFechaAtencion());
            holder.tvFecha.setText(fecha[0]);
            holder.tvDiaSemana.setText(fecha[1]);

            holder.btnPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dexter.withActivity((Activity) ctx)
                            .withPermission(Manifest.permission.CAMERA)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    utils.createPdf();
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {
                                    // check for permanent denial of permission
                                    if (response.isPermanentlyDenied()) {
                                        //TODO
                                    }
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();
                    File file = utils.createPDFAtencion();
                    Intent intent = new Intent(ctx, PdfViewerActivity.class);
                    intent.putExtra("pdf", file.getAbsolutePath());
                    ctx.startActivity(intent);

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
        public final TextView tvNumeroAtencion;
        public final TextView tvMedico;
        public final TextView tvFecha;
        public final TextView tvDiaSemana;
        public final Button btnPdf;
        public ResponseAtenciones mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNumeroAtencion = (TextView) view.findViewById(R.id.tvNumeroAtencion);
            tvMedico = (TextView) view.findViewById(R.id.tvMedico);
            tvFecha = (TextView) view.findViewById(R.id.tvFechaAtencion);
            tvDiaSemana = (TextView) view.findViewById(R.id.tvDiaSemana);
            btnPdf = (Button) view.findViewById(R.id.btnPdf);
        }

    }
}