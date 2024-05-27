package com.tfgjunio.model.Baja;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Baja.AddBajaContract;
import com.tfgjunio.domain.Baja;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBajaModel implements AddBajaContract.Model {

    @Override
    public void addBaja(Baja baja, OnRegisterBajaListener listener) {
        try {
            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Baja> callBajas = junioApiInterface.addBaja(baja);
            Log.d("bajas", "llamada desde el AddBajaModel");
            callBajas.enqueue(new Callback<Baja>() {
                @Override
                public void onResponse(Call<Baja> call, Response<Baja> response) {
                    Baja baja = response.body();
                    listener.onRegisterSuccess(baja);
                }

                @Override
                public void onFailure(Call<Baja> call, Throwable t) {
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
