package com.tfgjunio.model.Compra;

import android.content.Context;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Compra.CompraListContract;
import com.tfgjunio.domain.Compra;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompraListModel implements CompraListContract.Model {

    private Context context;

    public CompraListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllCompras(CompraListContract.Model.OnLoadCompraListener listener) {

        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Compra>> callCompras = junioApiInterface.getCompras();
        Log.d("Compra", "llamada desde Compra model");
        callCompras.enqueue(new Callback<List<Compra>>() {
            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                Log.d("Compra", "llamada desde el Compra model OK");
                List<Compra>compras = response.body();
                listener.onLoadComprasSuccess(compras);
            }

            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                Log.d("Compra", "llamada desde el Compra model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadComprasError(message);

            }
        });
    }
}
