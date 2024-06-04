package com.tfgjunio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tfgjunio.R;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.Baja;
import com.tfgjunio.domain.Recurso;
import java.util.ArrayList;
import java.util.List;

public class CrianzaAdapter extends RecyclerView.Adapter<CrianzaAdapter.CrianzaViewHolder> {

    private List<Crianza> crianzaList;

    public CrianzaAdapter(List<Crianza> crianzaList) {
        this.crianzaList = crianzaList;
    }

    @NonNull
    @Override
    public CrianzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crianza_todas_item, parent, false);
        return new CrianzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrianzaViewHolder holder, int position) {
        Crianza crianza = crianzaList.get(position);
        holder.tvCrianzaFechas.setText("Fecha Inicio: " + crianza.getFechaInicio() + "\nFecha Fin: " + (crianza.getFechaFin() != null ? crianza.getFechaFin() : "N/A"));

        holder.itemView.setOnClickListener(v -> {
            if (holder.layoutCrianzaDetails.getVisibility() == View.GONE) {
                holder.layoutCrianzaDetails.setVisibility(View.VISIBLE);
                displayDetails(holder, crianza);
                setupPieChart(holder.pieChartBajas, crianza.getCrianzaBaja());
            } else {
                holder.layoutCrianzaDetails.setVisibility(View.GONE);
            }
        });
    }

    private void displayDetails(CrianzaViewHolder holder, Crianza crianza) {
        holder.layoutAnimales.removeAllViews();
        holder.layoutCompras.removeAllViews();
        holder.layoutBajas.removeAllViews();

        for (Animal animal : crianza.getCrianzaAnimal()) {
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText("Número: " + animal.getNumero() +
                    "\nSexo: " + animal.getSexo() +
                    "\nPeso: " + animal.getPeso() +
                    "\nTipo: " + animal.getAnimalTipoAnimal().getNombre());
            holder.layoutAnimales.addView(textView);
        }

        for (Compra compra : crianza.getCrianzaCompra()) {
            TextView textView = new TextView(holder.itemView.getContext());
            StringBuilder recursos = new StringBuilder();
            for (Recurso recurso : compra.getCompraRecurso()) {
                if (recursos.length() > 0) recursos.append(", ");
                recursos.append(recurso.getNombre());
            }
            textView.setText("Fecha: " + compra.getFechaCompra() +
                    "\nDescripción: " + compra.getDescripcion() +
                    "\nRecursos: " + recursos.toString());
            holder.layoutCompras.addView(textView);
        }

        for (Baja baja : crianza.getCrianzaBaja()) {
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText("Fecha: " + baja.getFecha() +
                    "\nCantidad: " + baja.getCantidad() +
                    "\nTipo: " + baja.getBajaTipoBaja().getNombre());
            holder.layoutBajas.addView(textView);
        }
    }

    private void setupPieChart(PieChart pieChart, List<Baja> bajas) {
        List<PieEntry> entries = new ArrayList<>();
        for (Baja baja : bajas) {
            entries.add(new PieEntry(baja.getCantidad(), baja.getBajaTipoBaja().getNombre()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "Bajas por Tipo");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    @Override
    public int getItemCount() {
        return crianzaList.size();
    }

    static class CrianzaViewHolder extends RecyclerView.ViewHolder {
        TextView tvCrianzaFechas;
        LinearLayout layoutCrianzaDetails, layoutAnimales, layoutCompras, layoutBajas;
        PieChart pieChartBajas;

        CrianzaViewHolder(View itemView) {
            super(itemView);
            tvCrianzaFechas = itemView.findViewById(R.id.tvCrianzaFechas);
            layoutCrianzaDetails = itemView.findViewById(R.id.layoutCrianzaDetails);
            layoutAnimales = itemView.findViewById(R.id.layoutAnimales);
            layoutCompras = itemView.findViewById(R.id.layoutCompras);
            layoutBajas = itemView.findViewById(R.id.layoutBajas);
            pieChartBajas = itemView.findViewById(R.id.pieChartBajas);
        }
    }
}
