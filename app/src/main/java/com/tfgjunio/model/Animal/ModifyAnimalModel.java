package com.tfgjunio.model.Animal;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Animal.ModifyAnimalContract;
import com.tfgjunio.domain.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyAnimalModel implements ModifyAnimalContract.Model {

    @Override
    public void modifyAnimal(long id, Animal animal, OnModifyAnimalListener listener) {
        try {
            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Animal> callAnimales = junioApiInterface.modifyAnimal(id, animal.getAnimalTipoAnimal().getId(), animal.getAnimalCrianza().getId(), animal);
            Log.d("Animal", "llamada desde el ModifyAnimalModel");
            callAnimales.enqueue(new Callback<Animal>() {
                @Override
                public void onResponse(Call<Animal> call, Response<Animal> response) {
                    Log.d("Animal", "llamada desde el ModifyAnimalModel OK");
                    if (response.isSuccessful()) {
                        Animal modifiedAnimal = response.body();
                        listener.onModifySuccess(modifiedAnimal);
                    } else {
                        listener.onModifyError("Error en la respuesta del servidor");
                    }
                }

                @Override
                public void onFailure(Call<Animal> call, Throwable t) {
                    Log.d("Animal", "llamada desde el ModifyAnimalModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
