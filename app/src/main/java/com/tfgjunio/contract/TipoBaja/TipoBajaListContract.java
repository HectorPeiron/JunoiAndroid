package com.tfgjunio.contract.tipobaja;

import com.tfgjunio.domain.TipoBaja;

import java.util.List;

public interface TipoBajaListContract {
    interface Model {
        interface OnLoadTipoBajaListener {
            void onLoadTipoBajasSuccess(List<TipoBaja> tipoBajas);

            void onLoadTipoBajasError(String message);
        }

        void loadAllTipoBajas(OnLoadTipoBajaListener listener);
    }

    interface View {
        void showTipoBajas(List<TipoBaja> tipoBajas);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllTipoBajas();
    }
}
