package com.tfgjunio.model.Veterinario;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Veterinario.ModifyVeterinarioContract;
import com.tfgjunio.domain.Veterinario;
import com.tfgjunio.presenter.Veterinario.ModifyVeterinarioPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyVeterinarioModel implements ModifyVeterinarioContract.Model {
    private ModifyVeterinarioPresenter presenter;

    @Override
    public void modifyVeterinario(long id, Veterinario veterinario, OnModifyVeterinarioListener listener) {

        try {
            JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
            Call<Veterinario> callVeterinarios = junioApiInterface.modifyVeterinario(id, veterinario);
            Log.d("Veterinario", "llamada desde el Modify Veterinario Model");
            callVeterinarios.enqueue(new Callback<Veterinario>() {
                @Override
                public void onResponse(Call<Veterinario> call, Response<Veterinario> response) {
                    Log.d("Veterinario", "llamada desde el ModifyVeterinarioModel OK");
                    listener.onModifySuccess(veterinario);
                }

                @Override
                public void onFailure(Call<Veterinario> call, Throwable t) {
                    Log.d("Veterinario", "llamada desde el DeleteVeterinarioModel KO");
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