package com.tfgjunio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tfgjunio.R;
import com.tfgjunio.domain.Compra;

import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.CompraViewHolder> {

    private List<Compra> compraList;
    private Context context;

    public CompraAdapter(Context context, List<Compra> compraList) {
        this.context = context;
        this.compraList = compraList;
    }

    @Override
    public CompraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compra, parent, false);
        return new CompraViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompraViewHolder holder, int position) {
        Compra compra = compraList.get(position);
        holder.tvFechaCompra.setText(compra.getFechaCompra().toString());
        holder.tvDescripcion.setText(compra.getDescripcion());

        /**  FALTA AÑADIR LOS RECURSOS Y LA CRIANZA */

    }

    @Override
    public int getItemCount() {
        return compraList.size();
    }

    public static class CompraViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFechaCompra;
        public TextView tvDescripcion;
        /**  FALTA AÑADIR LOS RECURSOS Y LA CRIANZA */

        public CompraViewHolder(View view) {
            super(view);
            tvFechaCompra = view.findViewById(R.id.tvFechaCompra);
            tvDescripcion = view.findViewById(R.id.tvDescripcion);
            /**  FALTA AÑADIR LOS RECURSOS Y LA CRIANZA */
        }
    }
}
