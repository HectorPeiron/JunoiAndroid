package com.tfgjunio.contract.Unidad;

import com.tfgjunio.domain.Unidad;

import java.util.List;

public interface UnidadListContract {
    interface Model {
        interface OnLoadUnidadListener {
            void onLoadUnidadesSuccess(List<Unidad> unidades);

            void onLoadUnidadesError(String message);
        }

        void loadAllUnidades(OnLoadUnidadListener listener);
    }

    interface View {
        void showUnidades(List<Unidad> unidades);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllUnidades();
    }
}
