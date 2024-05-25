package com.tfgjunio.contract.Crianza;

import com.tfgjunio.domain.Crianza;

import java.util.List;

public interface CrianzaListContract {
    interface Model {
        interface OnLoadCrianzaListener {
            void onLoadCrianzasSuccess(List<Crianza> crianzas);

            void onLoadCrianzasError(String message);
        }

        void loadAllCrianzas(CrianzaListContract.Model.OnLoadCrianzaListener listener);
    }

    interface View {
        void showCrianzas(List<Crianza> crianzas);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllCrianzas();
    }

}
