package com.tfgjunio.view.Animal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.tfgjunio.R;
import com.tfgjunio.adapter.AnimalAdapter;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.utils.PreferencesHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalListView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimalAdapter animalAdapter;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        preferencesHelper = new PreferencesHelper(this);
        recyclerView = findViewById(R.id.animalRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadAnimals();
    }

    private void loadAnimals() {
        long crianzaId = preferencesHelper.getCrianzaId();
        if (crianzaId == -1) {
            Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            return;
        }

        JunioAPI.buildInstance().getAnimalesByCrianzaId(crianzaId).enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    animalAdapter = new AnimalAdapter(AnimalListView.this, response.body());
                    recyclerView.setAdapter(animalAdapter);
                } else {
                    Toast.makeText(AnimalListView.this, "Error al cargar animales: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Toast.makeText(AnimalListView.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }
}
