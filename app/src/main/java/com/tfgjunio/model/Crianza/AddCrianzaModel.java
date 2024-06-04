package com.tfgjunio.model.Crianza;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Crianza.AddCrianzaContract;
import com.tfgjunio.domain.Crianza;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCrianzaModel implements AddCrianzaContract.Model {

    @Override
    public void addCrianza(Crianza crianza, OnRegisterCrianzaListener listener) {
        try {
            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Crianza> callCrianza = junioApiInterface.addCrianza(crianza);
            Log.d("Crianza", "Llamada desde AddCrianzaModel");
            callCrianza.enqueue(new Callback<Crianza>() {
                @Override
                public void onResponse(Call<Crianza> call, Response<Crianza> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Crianza newCrianza = response.body();
                        listener.onRegisterSuccess(newCrianza);
                    } else {
                        String errorMessage = "Error en la respuesta: " + response.code() + " " + response.message();
                        Log.e("Crianza", errorMessage);
                        listener.onRegisterError(errorMessage);
                    }
                }

                @Override
                public void onFailure(Call<Crianza> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación: " + t.getMessage();
                    Log.e("Crianza", message);
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            String message = "Error: " + sce.getMessage();
            Log.e("Crianza", message);
            listener.onRegisterError(message);
        }
    }
}
