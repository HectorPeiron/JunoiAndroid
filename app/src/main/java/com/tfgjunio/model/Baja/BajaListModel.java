package com.tfgjunio.model.Baja;

import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Baja.BajaListContract;
import com.tfgjunio.domain.Baja;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BajaListModel implements BajaListContract.Model {

    @Override
    public void loadAllBajas(OnLoadBajaListener listener) {
        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Baja>> callBajas = junioApiInterface.getBajas();
        Log.d("bajas", "llamada desde el BajaListModel");
        callBajas.enqueue(new Callback<List<Baja>>() {
            @Override
            public void onResponse(Call<List<Baja>> call, Response<List<Baja>> response) {
                Log.d("Bajas", "llamada desde el Bajas model OK");
                List<Baja> bajas = response.body();
                listener.onLoadBajasSuccess(bajas);
            }

            @Override
            public void onFailure(Call<List<Baja>> call, Throwable t) {
                Log.d("Bajas", "llamada desde el Bajas model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadBajasError(message);
            }
        });
    }
}
