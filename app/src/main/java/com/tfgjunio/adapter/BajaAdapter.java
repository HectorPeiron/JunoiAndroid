package com.tfgjunio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tfgjunio.R;
import com.tfgjunio.domain.Baja;

import java.util.List;

public class BajaAdapter extends RecyclerView.Adapter<BajaAdapter.BajaViewHolder> {
    private Context context;
    private List<Baja> bajaList;

    public BajaAdapter(Context context, List<Baja> bajaList) {
        this.context = context;
        this.bajaList = bajaList;
    }

    @NonNull
    @Override
    public BajaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.baja_item, parent, false);
        return new BajaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BajaViewHolder holder, int position) {
        Baja baja = bajaList.get(position);
        holder.fecha.setText("Fecha: " + baja.getFecha().toString());
        holder.cantidad.setText("Cantidad: " + baja.getCantidad());
        holder.tipoBaja.setText("Tipo de Baja: " + baja.getBajaTipoBaja().getNombre());
    }

    @Override
    public int getItemCount() {
        return bajaList.size();
    }

    static class BajaViewHolder extends RecyclerView.ViewHolder {
        TextView fecha, cantidad, tipoBaja;

        public BajaViewHolder(View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.bajaFecha);
            cantidad = itemView.findViewById(R.id.bajaCantidad);
            tipoBaja = itemView.findViewById(R.id.bajaTipo);
        }
    }
}
