package com.tfgjunio.contract.Crianza;

import com.tfgjunio.domain.Crianza;

public interface ModifyCrianzaContract {

    interface Model {
        interface OnModifyCrianzaListener {
            void onModifySuccess(Crianza crianza);

            void onModifyError(String message);
        }

        void modifyCrianza(long id, Crianza crianza, ModifyCrianzaContract.Model.OnModifyCrianzaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyCrianza(long id, Crianza crianza);
    }
}

