package com.tfgjunio.contract.TipoAnimal;

import com.tfgjunio.domain.TipoAnimal;

import java.util.List;

public interface TipoAnimalListContract {
    interface Model {
        interface OnLoadTipoAnimalListener {
            void onLoadTipoAnimalesSuccess(List<TipoAnimal> tipoAnimales);

            void onLoadTipoAnimalesError(String message);
        }

        void loadAllTipoAnimales(OnLoadTipoAnimalListener listener);
    }

    interface View {
        void showTipoAnimales(List<TipoAnimal> tipoAnimales);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllTipoAnimales();
    }
}
