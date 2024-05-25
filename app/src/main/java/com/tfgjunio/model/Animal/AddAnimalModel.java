package com.tfgjunio.model.Animal;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Animal.AddAnimalContract;
import com.tfgjunio.domain.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAnimalModel implements AddAnimalContract.Model {

    @Override
    public void addAnimal(Animal animal, OnRegisterAnimalListener listener) {
        try {

            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Animal> callAnimales = junioApiInterface.addAnimal(animal);
            Log.d("animales", "llamada desde el addAnimalModel");
            callAnimales.enqueue(new Callback<Animal>() {
                @Override
                public void onResponse(Call<Animal> call, Response<Animal> response) {
                    Animal animal = response.body();
                    listener.onRegisterSuccess(animal);
                }

                @Override
                public void onFailure(Call<Animal> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}

