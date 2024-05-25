package com.tfgjunio.model.Recurso;

import android.content.Context;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.domain.Recurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecursoListModel implements RecursoListContract.Model {

    private Context context;


    @Override
    public void loadAllRecursos(OnLoadRecursoListener listener) {

        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Recurso>> callRecursos = junioApiInterface.getRecursos();

        Log.d("Recurso", "llamada desde el Recurso model");
        callRecursos.enqueue(new Callback<List<Recurso>>() {
            @Override
            public void onResponse(Call<List<Recurso>> call, Response<List<Recurso>> response) {
                Log.d("Recurso", "llamada desde el Recurso model OK");
                List<Recurso>recursos = response.body();
                listener.onLoadRecursosSuccess(recursos);
            }

            @Override
            public void onFailure(Call<List<Recurso>> call, Throwable t) {
                Log.d("Recurso", "llamada desde el Recurso model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadRecursosError(message);

            }
        });
    }
}

