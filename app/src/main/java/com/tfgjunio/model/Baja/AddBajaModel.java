package com.tfgjunio.model.Baja;

import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.contract.Baja.AddBajaContract;
import com.tfgjunio.domain.Baja;
import com.tfgjunio.domain.TipoBaja;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBajaModel implements AddBajaContract.Model {

    @Override
    public void loadTipoBajas(OnTipoBajasLoadedListener listener) {
        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<List<TipoBaja>> call = apiService.getTipoBajas();
        call.enqueue(new Callback<List<TipoBaja>>() {
            @Override
            public void onResponse(Call<List<TipoBaja>> call, Response<List<TipoBaja>> response) {
                if (response.isSuccessful()) {
                    listener.onTipoBajasLoaded(response.body());
                } else {
                    listener.onLoadError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<TipoBaja>> call, Throwable t) {
                listener.onLoadError("Failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void addBaja(Baja baja, OnBajaAddedListener listener) {
        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<Baja> call = apiService.addBaja(baja);
        call.enqueue(new Callback<Baja>() {
            @Override
            public void onResponse(Call<Baja> call, Response<Baja> response) {
                if (response.isSuccessful()) {
                    listener.onBajaAdded();
                } else {
                    listener.onAddError("Error adding baja: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Baja> call, Throwable t) {
                listener.onAddError("Failure: " + t.getMessage());

            }
        });
    }
}
