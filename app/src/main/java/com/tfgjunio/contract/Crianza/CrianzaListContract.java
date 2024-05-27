package com.tfgjunio.contract.Crianza;

import com.tfgjunio.domain.Crianza;

import java.util.List;

public interface CrianzaListContract {
    interface Model {
        interface OnLoadCrianzaListener {
            void onLoadCrianzasSuccess(List<Crianza> crianzas);

            void onLoadCrianzasError(String message);
        }

        void loadAllCrianzas(OnLoadCrianzaListener listener);
    }

    interface View {
        void showCrianza(List<Crianza> crianzas);

        void showModifyMessage(String message);
    }

    interface Presenter {
        void loadAllCrianza();
    }
}
