package com.tfgjunio.contract.Baja;

import com.tfgjunio.domain.Baja;
import com.tfgjunio.domain.TipoBaja;

import java.util.List;

public interface AddBajaContract {
    interface View {
        void showTipoBajas(List<TipoBaja> tipoBajas);
        void showError(String message);
        void showMessage(String message);
        void onBajaAdded();
    }

    interface Presenter {
        void loadTipoBajas();
        void addBaja(Baja baja);
    }

    interface Model {
        interface OnTipoBajasLoadedListener {
            void onTipoBajasLoaded(List<TipoBaja> tipoBajas);
            void onLoadError(String message);
        }

        interface OnBajaAddedListener {
            void onBajaAdded();
            void onAddError(String message);
        }

        void loadTipoBajas(OnTipoBajasLoadedListener listener);
        void addBaja(Baja baja, OnBajaAddedListener listener);
    }
}
