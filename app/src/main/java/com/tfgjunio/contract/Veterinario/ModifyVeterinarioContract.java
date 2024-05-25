package com.tfgjunio.contract.Veterinario;

import com.tfgjunio.domain.Veterinario;

public interface ModifyVeterinarioContract {

    interface Model {
        interface OnModifyVeterinarioListener {
            void onModifySuccess(Veterinario veterinario);

            void onModifyError(String message);
        }

        void modifyVeterinario(long id, Veterinario veterinario, ModifyVeterinarioContract.Model.OnModifyVeterinarioListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyVeterinario(long id, Veterinario veterinario);
    }
}



