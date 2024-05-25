package com.tfgjunio.contract.Crianza;

import com.tfgjunio.domain.Crianza;

public interface AddCrianzaContract {

    interface Model {
        interface OnRegisterCrianzaListener {
            void onRegisterSuccess(Crianza crianza);

            void onRegisterError(String message);
        }

        void addCrianza(Crianza crianza, OnRegisterCrianzaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addCrianza(Crianza crianza
        );
    }
}