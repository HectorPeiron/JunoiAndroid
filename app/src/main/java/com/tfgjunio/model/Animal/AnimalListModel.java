package com.tfgjunio.model.Animal;

import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Animal.AnimalListContract;
import com.tfgjunio.domain.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalListModel implements AnimalListContract.Model {

    @Override
    public void loadAllAnimales(OnLoadAnimalListener listener) {
        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Animal>> callAnimales = junioApiInterface.getAnimales();
        Log.d("animales", "llamada desde el AnimalListModel");
        callAnimales.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                Log.d("Animales", "llamada desde el Animales model OK");
                List<Animal> animales = response.body();
                listener.onLoadAnimalesSuccess(animales);
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.d("Animales", "llamada desde el Animales model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadAnimalesError(message);
            }
        });
    }
}
