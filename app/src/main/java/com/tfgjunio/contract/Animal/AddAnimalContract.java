package com.tfgjunio.contract.Animal;

import com.tfgjunio.domain.Animal;
import com.tfgjunio.domain.TipoAnimal;

import java.util.List;

public interface AddAnimalContract {
    interface View {
        void showTipoAnimales(List<TipoAnimal> tipoAnimales);
        void showError(String message);
        void showMessage(String message);
        void onAnimalAdded(Animal animal);
    }

    interface Presenter {
        void loadTipoAnimales();
        void addAnimal(Animal animal);
    }

    interface Model {
        interface OnTipoAnimalesLoadedListener {
            void onTipoAnimalesLoaded(List<TipoAnimal> tipoAnimales);
            void onLoadError(String message);
        }

        interface OnAnimalAddedListener {
            void onAnimalAdded(Animal animal);
            void onAddError(String message);
        }

        void loadTipoAnimales(OnTipoAnimalesLoadedListener listener);
        void addAnimal(Animal animal, OnAnimalAddedListener listener);
    }
}
