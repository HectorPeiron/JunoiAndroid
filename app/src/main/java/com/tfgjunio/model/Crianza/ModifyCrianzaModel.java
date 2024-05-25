package com.tfgjunio.model.Crianza;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.domain.Crianza;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyCrianzaModel  implements ModifyCrianzaContract.Model {
    private ModifyCrianzaPresenter presenter;

    @Override
    public void modifyCrianza(long id, Crianza crianza, OnModifyCrianzaListener listener) {

        try {
            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Crianza> callCrianzas = junioApiInterface.modifyCrianza(id, crianza);
            Log.d("Crianza", "llamada desde el Modify Crianza Model");
            callCrianzas.enqueue(new Callback<Crianza>() {
                @Override
                public void onResponse(Call<Crianza> call, Response<Crianza> response) {
                    Log.d("Crianza", "llamada desde el ModifyCrianzaModel OK");
                    listener.onModifySuccess(crianza);
                }

                @Override
                public void onFailure(Call<Crianza> call, Throwable t) {
                    Log.d("Crianza", "llamada desde el DeleteCrianzaModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}