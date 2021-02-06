package com.example.prototipo.ui.resultados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prototipo.R;
import com.example.prototipo.common.Ecografia;
import com.example.prototipo.common.Utils;
import com.example.prototipo.common.Vacuna;

import java.util.List;

public class MyEcografiaRecyclerViewAdapter extends RecyclerView.Adapter<MyEcografiaRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Ecografia> mValues;

    private Utils utils;

    public MyEcografiaRecyclerViewAdapter(Context context, List<Ecografia> items) {
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
            holder.tvTipo.setText(holder.mItem.getTipo());

            String[] fecha = utils.getDayOfWeek(holder.mItem.getFecha());
            holder.tvFechaAtencion.setText(fecha[0]);
            holder.tvDiaSemana.setText(fecha[1]);

            holder.btnPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        public final TextView tvTipo;
        public final TextView tvFechaAtencion;
        public final TextView tvDiaSemana;
        public final Button btnPdf;
        public Ecografia mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvSituacion = (TextView) view.findViewById(R.id.tvSituacion);
            tvTipo = (TextView) view.findViewById(R.id.tvTipo);
            tvFechaAtencion = (TextView) view.findViewById(R.id.tvFechaAtencion);
            tvDiaSemana = (TextView) view.findViewById(R.id.tvDiaSemana);
            btnPdf = (Button) view.findViewById(R.id.btnPdf);
        }

    }
}
