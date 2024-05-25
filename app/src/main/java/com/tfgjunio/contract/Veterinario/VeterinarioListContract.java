package com.tfgjunio.contract.Veterinario;

import com.tfgjunio.domain.Veterinario;

import java.util.List;

public interface VeterinarioListContract {

    interface Model {
        interface OnLoadVeterinarioListener {
            void onLoadVeterinariosSuccess(List<Veterinario> veterinarios);

            void onLoadVeterinariosError(String message);
        }

        void loadAllVeterinarios(OnLoadVeterinarioListener listener);
    }

    interface View {
        void showVeterinarios(List<Veterinario> veterinarios);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllVeterinarios();
    }
}
