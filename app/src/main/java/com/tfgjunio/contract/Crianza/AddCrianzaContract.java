package com.tfgjunio.contract.Crianza;

import com.tfgjunio.domain.Crianza;

public interface AddCrianzaContract {
    interface View {
        void showError(String message);
        void showMessage(String message);
        void onCrianzaAdded(Crianza crianza);
    }

    interface Presenter {
        void addCrianza(Crianza crianza);
    }

    interface Model {
        interface OnRegisterCrianzaListener {
            void onRegisterSuccess(Crianza crianza);
            void onRegisterError(String error);
        }

        void addCrianza(Crianza crianza, OnRegisterCrianzaListener listener);
    }
}
