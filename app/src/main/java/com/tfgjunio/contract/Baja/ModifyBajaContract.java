package com.tfgjunio.contract.Baja;

import com.tfgjunio.domain.Baja;

public interface ModifyBajaContract {

    interface Model {
        interface OnModifyBajaListener {
            void onModifySuccess(Baja baja);

            void onModifyError(String message);
        }

        void modifyBaja(long id, Baja baja, OnModifyBajaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyBaja(long id, Baja baja);
    }
}
