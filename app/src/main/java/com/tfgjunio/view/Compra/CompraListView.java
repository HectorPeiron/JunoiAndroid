package com.tfgjunio.view.Compra;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tfgjunio.R;
import com.tfgjunio.adapter.CompraAdapter;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.utils.PreferencesHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompraListView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CompraAdapter compraAdapter;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_list);

        preferencesHelper = new PreferencesHelper(this);
        recyclerView = findViewById(R.id.compraRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCompras();
    }

    private void loadCompras() {
        long crianzaId = preferencesHelper.getCrianzaId();
        if (crianzaId == -1) {
            Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            return;
        }

        JunioAPI.buildInstance().getComprasByCrianzaId(crianzaId).enqueue(new Callback<List<Compra>>() {

            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    compraAdapter = new CompraAdapter(CompraListView.this, response.body());
                    recyclerView.setAdapter(compraAdapter);
                } else {
                    Toast.makeText(CompraListView.this, "Error loading data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                Toast.makeText(CompraListView.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
