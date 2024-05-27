package com.tfgjunio.contract.Animal;

import com.tfgjunio.domain.Animal;

import java.util.List;

public interface AnimalListContract {
    interface Model {
        interface OnLoadAnimalListener {
            void onLoadAnimalesSuccess(List<Animal> animales);

            void onLoadAnimalesError(String message);
        }

        void loadAllAnimales(OnLoadAnimalListener listener);
    }

    interface View {
        void showAnimales(List<Animal> animals);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllAnimales();
    }
}
