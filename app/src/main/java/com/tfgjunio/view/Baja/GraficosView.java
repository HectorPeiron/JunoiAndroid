package com.tfgjunio.view.Baja;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tfgjunio.R;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.domain.Baja;
import com.tfgjunio.utils.PreferencesHelper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraficosView extends AppCompatActivity {

    private BarChart barChart;
    private PieChart pieChart;
    private Button btnVolver;
    private PreferencesHelper preferencesHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas_graficos);

        preferencesHelper = new PreferencesHelper(this);
        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(v -> {
            onBackPressed();

        });

        loadBajaData();
    }

    private void loadBajaData() {
        long crianzaId = preferencesHelper.getCrianzaId();
        if (crianzaId == -1) {
            Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            return;
        }

        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<List<Baja>> call = apiService.getBajasByCrianzaId(crianzaId);
        call.enqueue(new Callback<List<Baja>>() {
            @Override
            public void onResponse(Call<List<Baja>> call, Response<List<Baja>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    setupBarChart(response.body());
                    setupPieChart(response.body());
                } else {
                    Toast.makeText(GraficosView.this, "No hay bajas para la crianza actual", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Baja>> call, Throwable t) {
                Toast.makeText(GraficosView.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void printBajas(List<Baja> bajas) {
        for (Baja baja : bajas) {
            String logMessage = "ID: " + baja.getId() + ", Fecha: " + baja.getFecha() + ", Cantidad: " + baja.getCantidad() + ", Tipo Baja: " + (baja.getBajaTipoBaja() != null ? baja.getBajaTipoBaja().getNombre() : "N/A");
            System.out.println(logMessage);  // Imprimir en la consola de Logcat
        }
    }

    private List<Baja> filterBajasByCrianza(List<Baja> bajas, long crianzaId) {
        List<Baja> filteredBajas = new ArrayList<>();
        for (Baja baja : bajas) {
            if (baja.getBajaCrianza() != null && baja.getBajaCrianza().getId() == crianzaId) {
                filteredBajas.add(baja);
            }
        }
        return filteredBajas;
    }

    private class DateValueFormatter extends ValueFormatter {
        private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
        private final List<Long> dates;

        public DateValueFormatter(List<Long> dates) {
            this.dates = dates;
        }

        @Override
        public String getFormattedValue(float value) {
            if (value >= 0 && value < dates.size()) {
                return mFormat.format(new Date(dates.get((int) value)));
            }
            return "";
        }
    }

    private void setupBarChart(List<Baja> bajas) {
        List<BarEntry> entries = new ArrayList<>();
        HashMap<Long, Integer> fechaMap = new HashMap<>();
        List<Long> dates = new ArrayList<>();

        for (Baja baja : bajas) {
            long dateMillis = baja.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            int cantidad = baja.getCantidad();
            fechaMap.put(dateMillis, fechaMap.getOrDefault(dateMillis, 0) + cantidad);

        }

        int index = 0;
        for (Map.Entry<Long, Integer> entry : fechaMap.entrySet()) {
            entries.add(new BarEntry(index++, entry.getValue()));
            dates.add(entry.getKey());
        }

        BarDataSet dataSet = new BarDataSet(entries, "Bajas por día");
        BarData data = new BarData(dataSet);
        barChart.setData(data);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new DateValueFormatter(dates));

        barChart.invalidate();
        barChart.getDescription().setEnabled(false);

    }

    private void setupPieChart(List<Baja> bajas) {
        HashMap<String, Integer> tipoMap = new HashMap<>();

        for (Baja baja : bajas) {
            String tipo = baja.getBajaTipoBaja().getNombre();
            int cantidad = baja.getCantidad();
            tipoMap.put(tipo, tipoMap.getOrDefault(tipo, 0) + cantidad);
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : tipoMap.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Tipos de Baja");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.getDescription().setEnabled(false);
    }
}
