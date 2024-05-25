package com.tfgjunio.contract.Compra;

import com.tfgjunio.domain.Compra;

public interface AddCompraContract {

    interface Model {
        interface OnRegisterCompraListener {
            void onRegisterSuccess(Compra compra);

            void onRegisterError(String message);
        }

        void addCompra(Compra compra, OnRegisterCompraListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addCompra(Compra compra);
    }
}

