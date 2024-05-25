package com.tfgjunio.model.Crianza;

import android.content.Context;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.domain.Crianza;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrianzaListModel implements CrianzaListContract.Model {

    private Context context;

    public CrianzaListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllCrianzas(OnLoadCrianzaListener listener) {

        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Crianza>> callCrianzas = junioApiInterface.getCrianzas();
        Log.d("Crianza", "llamada desde el Crianza model");
        callCrianzas.enqueue(new Callback<List<Crianza>>() {
            @Override
            public void onResponse(Call<List<Crianza>> call, Response<List<Crianza>> response) {
                Log.d("Crianza", "llamada desde el Crianza model OK");
                List<Crianza>crianzas = response.body();
                listener.onLoadCrianzasSuccess(crianzas);
            }

            @Override
            public void onFailure(Call<List<Crianza>> call, Throwable t) {
                Log.d("Crianza", "llamada desde el Crianza model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadCrianzasError(message);

            }
        });
    }
}

