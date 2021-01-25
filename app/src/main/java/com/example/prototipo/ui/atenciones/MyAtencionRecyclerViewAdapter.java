package com.example.prototipo.ui.atenciones;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;

import java.util.List;


public class MyAtencionRecyclerViewAdapter extends RecyclerView.Adapter<MyAtencionRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<ResponseAtenciones> mValues;

    public MyAtencionRecyclerViewAdapter(Context context, List<ResponseAtenciones> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_atenciones, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (mValues != null) { //Solo se carga si la lista existe

            holder.mItem = mValues.get(position);

            //holder.tvNumeroAtencion.setText(position + 1);
            String nombreCompleto = holder.mItem.getPersonalObj().getNombre() + " " + holder.mItem.getPersonalObj().getApellido();
            holder.tvMedico.setText(nombreCompleto);
            holder.tvFecha.setText(holder.mItem.getFechaAtencion());

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
        public final Button btnPdf;
        public ResponseAtenciones mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNumeroAtencion = (TextView) view.findViewById(R.id.tvNumeroAtencion);
            tvMedico = (TextView) view.findViewById(R.id.tvMedico);
            tvFecha = (TextView) view.findViewById(R.id.tvFechaAtencion);
            btnPdf = (Button) view.findViewById(R.id.btnPdf);
        }

    }
}