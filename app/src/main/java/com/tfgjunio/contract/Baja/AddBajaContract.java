package com.tfgjunio.contract.Baja;

import com.tfgjunio.domain.Baja;

public interface AddBajaContract {

    interface Model {
        interface OnRegisterBajaListener {
            void onRegisterSuccess(Baja baja);

            void onRegisterError(String message);
        }

        void addBaja(Baja baja, OnRegisterBajaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addBaja(Baja baja);
    }
}
