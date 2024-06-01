package com.tfgjunio.view.Crianza;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tfgjunio.R;
import com.tfgjunio.adapter.CrianzaAdapter;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.domain.Crianza;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrianzaTodasListView extends AppCompatActivity {

    private RecyclerView recyclerViewCrianzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crianza_todas_list);

        recyclerViewCrianzas = findViewById(R.id.recyclerViewCrianzas);
        recyclerViewCrianzas.setLayoutManager(new LinearLayoutManager(this));

        loadCrianzas();


        // Obtener las dimensiones de la pantalla
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        Log.d("Screen Size", "Width: " + width + " Height: " + height);
    }


    private void loadCrianzas() {
        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<List<Crianza>> call = apiService.getCrianzas();
        call.enqueue(new Callback<List<Crianza>>() {
            @Override
            public void onResponse(Call<List<Crianza>> call, Response<List<Crianza>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Crianza> crianzaList = response.body();
                    CrianzaAdapter adapter = new CrianzaAdapter(crianzaList);
                    recyclerViewCrianzas.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Crianza>> call, Throwable t) {
                // Manejar error
            }
        });
    }
}
