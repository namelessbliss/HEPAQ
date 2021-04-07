package com.essaludapp.hepaq.ui.vacuna;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Utils;
import com.essaludapp.hepaq.common.Vacuna;


import java.util.List;

public class MyVacunaRecyclerViewAdapter extends RecyclerView.Adapter<MyVacunaRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Vacuna> mValues;

    private Utils utils;

    public MyVacunaRecyclerViewAdapter(Context context, List<Vacuna> items) {
        mValues = items;
        ctx = context;
        utils = new Utils();
    }

    @Override
    public MyVacunaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacuna_list_item, parent, false);
        return new MyVacunaRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyVacunaRecyclerViewAdapter.ViewHolder holder, int position) {

        if (mValues != null) { //Solo se carga si la lista existe

            holder.mItem = mValues.get(position);

            String nombreCompleto = holder.mItem.getNombre() + " " + holder.mItem.getApellido();
            holder.tvNombre.setText(nombreCompleto);
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
        public final TextView tvNombre;
        public final TextView tvTipo;
        public final TextView tvFechaAtencion;
        public final TextView tvDiaSemana;
        public final Button btnPdf;
        public Vacuna mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNombre = (TextView) view.findViewById(R.id.tvNombre);
            tvTipo = (TextView) view.findViewById(R.id.tvTipo);
            tvFechaAtencion = (TextView) view.findViewById(R.id.tvFechaAtencion);
            tvDiaSemana = (TextView) view.findViewById(R.id.tvDiaSemana);
            btnPdf = (Button) view.findViewById(R.id.btnPdf);
        }

    }
}
