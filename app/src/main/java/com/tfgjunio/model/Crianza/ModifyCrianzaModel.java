package com.tfgjunio.model.Crianza;

import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.contract.Crianza.ModifyCrianzaContract;
import com.tfgjunio.domain.Crianza;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyCrianzaModel implements ModifyCrianzaContract.Model {

    @Override
    public void modifyCrianza(long id, Crianza crianza, OnCrianzaModifiedListener listener) {
        JunioApiInterface apiService = JunioAPI.buildInstance();
        Call<Crianza> call = apiService.modifyCrianza(id, crianza);
        call.enqueue(new Callback<Crianza>() {
            @Override
            public void onResponse(Call<Crianza> call, Response<Crianza> response) {
                if (response.isSuccessful()) {
                    listener.onCrianzaModified(response.body());
                } else {
                    listener.onModificationError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Crianza> call, Throwable t) {
                listener.onModificationError("Error: " + t.getMessage());
            }
        });
    }
}
