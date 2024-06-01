package com.tfgjunio.view.Crianza;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tfgjunio.R;
import com.tfgjunio.adapter.AnimalAdapter;
import com.tfgjunio.adapter.CompraAdapter;
import com.tfgjunio.adapter.BajaAdapter;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Baja;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.utils.PreferencesHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrianzaDetailView extends AppCompatActivity {
    private TextView fechaInicio, fechaFin;
    private RecyclerView animalRecyclerView, compraRecyclerView, bajaRecyclerView;
    private AnimalAdapter animalAdapter;
    private CompraAdapter compraAdapter;
    private BajaAdapter bajaAdapter;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crianza_detail);

        preferencesHelper = new PreferencesHelper(this);
        fechaInicio = findViewById(R.id.crianzaFechaInicio);
        fechaFin = findViewById(R.id.crianzaFechaFin);
        animalRecyclerView = findViewById(R.id.animalRecyclerView);
        compraRecyclerView = findViewById(R.id.compraRecyclerView);
        bajaRecyclerView = findViewById(R.id.bajaRecyclerView);

        animalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        compraRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bajaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCrianzaDetails();
    }

    private void loadCrianzaDetails() {
        long crianzaId = preferencesHelper.getCrianzaId();
        if (crianzaId == -1) {
            Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            return;
        }

        JunioAPI.buildInstance().getCrianzaId(crianzaId).enqueue(new Callback<Crianza>() {
            @Override
            public void onResponse(Call<Crianza> call, Response<Crianza> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Crianza crianza = response.body();
                    fechaInicio.setText("Fecha de inicio: " + crianza.getFechaInicio().toString());
                    fechaFin.setText("Fecha de fin: " + (crianza.getFechaFin() != null ? crianza.getFechaFin().toString() : "Actual"));

                    loadAnimals(crianza.getId());
                    loadCompras(crianza.getId());
                    loadBajas(crianza.getId());
                } else {
                    Toast.makeText(CrianzaDetailView.this, "Error al cargar la crianza: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Crianza> call, Throwable t) {
                Toast.makeText(CrianzaDetailView.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadAnimals(long crianzaId) {
        JunioAPI.buildInstance().getAnimalesByCrianzaId(crianzaId).enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    animalAdapter = new AnimalAdapter(CrianzaDetailView.this, response.body());
                    animalRecyclerView.setAdapter(animalAdapter);
                } else {
                    Toast.makeText(CrianzaDetailView.this, "Error al cargar los animales: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Toast.makeText(CrianzaDetailView.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadCompras(long crianzaId) {
        JunioAPI.buildInstance().getComprasByCrianzaId(crianzaId).enqueue(new Callback<List<Compra>>() {
            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    compraAdapter = new CompraAdapter(CrianzaDetailView.this, response.body());
                    compraRecyclerView.setAdapter(compraAdapter);
                } else {
                    Toast.makeText(CrianzaDetailView.this, "Error al cargar las compras: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                Toast.makeText(CrianzaDetailView.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadBajas(long crianzaId) {
        JunioAPI.buildInstance().getBajasByCrianzaId(crianzaId).enqueue(new Callback<List<Baja>>() {
            @Override
            public void onResponse(Call<List<Baja>> call, Response<List<Baja>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bajaAdapter = new BajaAdapter(CrianzaDetailView.this, response.body());
                    bajaRecyclerView.setAdapter(bajaAdapter);
                } else {
                    Toast.makeText(CrianzaDetailView.this, "Error al cargar las bajas: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Baja>> call, Throwable t) {
                Toast.makeText(CrianzaDetailView.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }
}
