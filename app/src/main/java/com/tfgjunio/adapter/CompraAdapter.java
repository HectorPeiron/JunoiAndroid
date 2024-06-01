package com.tfgjunio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tfgjunio.R;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Recurso;

import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.ViewHolder> {

    private Context context;
    private List<Compra> compraList;

    public CompraAdapter(Context context, List<Compra> compraList) {
        this.context = context;
        this.compraList = compraList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.compra_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Compra compra = compraList.get(position);
        holder.fechaCompra.setText(compra.getFechaCompra().toString());
        holder.descripcionCompra.setText(compra.getDescripcion());
        StringBuilder recursos = new StringBuilder();
        for (Recurso recurso : compra.getCompraRecurso()) {
            if (recursos.length() > 0) recursos.append(", ");
            recursos.append(recurso.getNombre());
        }
        holder.recursosCompra.setText(recursos.toString());
    }

    @Override
    public int getItemCount() {
        return compraList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fechaCompra;
        public TextView descripcionCompra;
        public TextView recursosCompra;

        public ViewHolder(View itemView) {
            super(itemView);
            fechaCompra = itemView.findViewById(R.id.compraFecha);
            descripcionCompra = itemView.findViewById(R.id.compraDescripcion);
            recursosCompra = itemView.findViewById(R.id.compraRecurso);
        }
    }
}
