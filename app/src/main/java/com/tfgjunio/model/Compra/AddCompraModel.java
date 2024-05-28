package com.tfgjunio.model.Compra;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Recurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCompraModel implements AddCompraContract.Model {

    private JunioApiInterface apiService = JunioAPI.buildInstance();

    @Override
    public void addCompra(Compra compra, OnCompraAddedListener listener) {
        Call<Compra> call = apiService.addCompra(compra);
        call.enqueue(new Callback<Compra>() {
            @Override
            public void onResponse(Call<Compra> call, Response<Compra> response) {
                if (response.isSuccessful()) {
                    listener.onCompraAdded();
                } else {
                    listener.onAddError("Error adding purchase: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Compra> call, Throwable t) {
                listener.onAddError("Failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void loadRecursos(OnRecursosLoadedListener listener) {
        Call<List<Recurso>> call = apiService.getRecursos();
        call.enqueue(new Callback<List<Recurso>>() {
            @Override
            public void onResponse(Call<List<Recurso>> call, Response<List<Recurso>> response) {
                if (response.isSuccessful()) {
                    listener.onRecursosLoaded(response.body());
                } else {
                    listener.onLoadError("Error fetching resources: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Recurso>> call, Throwable t) {
                listener.onLoadError("Network error: " + t.getMessage());
            }
        });
    }
}
