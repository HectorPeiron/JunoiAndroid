package com.tfgjunio.model.Veterinario;

import android.content.Context;
import android.util.Log;

import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.domain.Veterinario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeterinarioListModel implements VeterinarioListContract.Model {

    private Context context;

    public VeterinarioListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllVeterinarios(OnLoadVeterinarioListener listener) {

        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Veterinario>> callVeterinarios = junioApiInterface.getVeterinarios();
        Log.d("Veterinario", "llamada desde el Veterinario model");
        callVeterinarios.enqueue(new Callback<List<Veterinario>>() {
            @Override
            public void onResponse(Call<List<Veterinario>> call, Response<List<Veterinario>> response) {
                Log.d("Veterinario", "llamada desde el Veterinario model OK");
                List<Veterinario>veterinarios = response.body();
                listener.onLoadVeterinarioSuccess(veterninarios);
            }

            @Override
            public void onFailure(Call<List<Veterinario>> call, Throwable t) {
                Log.d("Veterinario", "llamada desde el Veterinario model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadVeterinariosError(message);

            }
        });
    }
}

