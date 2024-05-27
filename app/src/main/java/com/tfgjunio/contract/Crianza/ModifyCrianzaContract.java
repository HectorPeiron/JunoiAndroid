package com.tfgjunio.contract.Crianza;

import com.tfgjunio.domain.Crianza;

public interface ModifyCrianzaContract {
    interface View {
        void onCrianzaModified(Crianza crianza);
        void showError(String message);
        void showMessage(String message);
    }

    interface Presenter {
        void modifyCrianza(long id, Crianza crianza);
    }

    interface Model {
        interface OnCrianzaModifiedListener {
            void onCrianzaModified(Crianza crianza);
            void onModificationError(String error);
        }

        void modifyCrianza(long id, Crianza crianza, OnCrianzaModifiedListener listener);
    }
}
