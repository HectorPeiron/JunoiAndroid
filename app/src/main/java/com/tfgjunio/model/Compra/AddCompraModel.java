package com.tfgjunio.model.Compra;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCompraModel implements AddCompraContract.Model {

    @Override
    public void addCompra(Compra compra, OnRegisterCompraListener listener) {
        try {
            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Compra> callCompras = junioApiInterface.addCompra(compra);
            Log.d("Compra", "llamada desde el addCompraModel");
            callCompras.enqueue(new Callback<Compra>() {
                @Override
                public void onResponse(Call<Compra> call, Response<Compra> response) {
                    Compra compra = response.body();
                    listener.onRegisterSuccess(compra);
                }

                @Override
                public void onFailure(Call<Compra> call, Throwable t) {
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

