package com.tfgjunio.model.Crianza;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.domain.Crianza;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCrianzaModel  implements AddCrianzaContract.Model {

    @Override
    public void addCrianza(Crianza crianza, OnRegisterCrianzaListener listener) {
        try {

            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Crianza> callCrianzas = junioApiInterface.addCrianza(crianza);
            Log.d("Crianzas", "llamada desde el addCrianzaModel");
            callCrianzas.enqueue(new Callback<Crianza>() {
                @Override
                public void onResponse(Call<Crianza> call, Response<Crianza> response) {
                    Crianza crianza = response.body();
                    listener.onRegisterSuccess(crianza);
                }

                @Override
                public void onFailure(Call<Crianza> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación Crianza";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}

