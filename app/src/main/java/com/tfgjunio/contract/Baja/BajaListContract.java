package com.tfgjunio.contract.Baja;

import com.tfgjunio.domain.Baja;

import java.util.List;

public interface BajaListContract {
    interface Model {
        interface OnLoadBajaListener {
            void onLoadBajasSuccess(List<Baja> bajas);

            void onLoadBajasError(String message);
        }

        void loadAllBajas(OnLoadBajaListener listener);
    }

    interface View {
        void showBajas(List<Baja> bajas);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllBajas();
    }
}
