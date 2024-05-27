package com.tfgjunio.model.Recurso;

import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Recurso.RecursoListContract;
import com.tfgjunio.domain.Recurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecursoListModel implements RecursoListContract.Model {

    public RecursoListModel() {
    }

    @Override
    public void loadAllRecursos(OnLoadRecursoListener listener) {
        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Recurso>> callRecursos = junioApiInterface.getRecursos();
        Log.d("recursos", "llamada desde el RecursoListModel");
        callRecursos.enqueue(new Callback<List<Recurso>>() {
            @Override
            public void onResponse(Call<List<Recurso>> call, Response<List<Recurso>> response) {
                Log.d("Recursos", "llamada desde el Recursos model OK");
                List<Recurso> recursos = response.body();
                if (recursos != null) {
                    listener.onLoadRecursosSuccess(recursos);
                } else {
                    listener.onLoadRecursosError("No se encontraron recursos");
                }
            }

            @Override
            public void onFailure(Call<List<Recurso>> call, Throwable t) {
                Log.d("Recursos", "llamada desde el Recursos model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadRecursosError(message);
            }
        });
    }
}
