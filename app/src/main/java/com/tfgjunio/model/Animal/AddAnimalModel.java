package com.tfgjunio.model.Animal;

import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.contract.Animal.AddAnimalContract;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.domain.TipoAnimal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAnimalModel implements AddAnimalContract.Model {

    @Override
    public void loadTipoAnimales(OnTipoAnimalesLoadedListener listener) {
        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<List<TipoAnimal>> call = apiService.getTipoAnimales();
        call.enqueue(new Callback<List<TipoAnimal>>() {
            @Override
            public void onResponse(Call<List<TipoAnimal>> call, Response<List<TipoAnimal>> response) {
                if (response.isSuccessful()) {
                    listener.onTipoAnimalesLoaded(response.body());
                } else {
                    listener.onLoadError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<TipoAnimal>> call, Throwable t) {
                listener.onLoadError("Failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void addAnimal(Animal animal, OnAnimalAddedListener listener) {
        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<Animal> call = apiService.addAnimal(animal);
        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.isSuccessful()) {
                    listener.onAnimalAdded(response.body());
                } else {
                    listener.onAddError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                listener.onAddError("Failure: " + t.getMessage());
            }
        });
    }
}
