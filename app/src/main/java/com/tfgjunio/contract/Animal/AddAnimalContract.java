package com.tfgjunio.contract.Animal;

import com.tfgjunio.domain.Animal;

public interface AddAnimalContract {

    interface Model {
        interface OnRegisterAnimalListener {
            void onRegisterSuccess(Animal animal);

            void onRegisterError(String message);
        }

        void addAnimal(Animal animal, OnRegisterAnimalListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addAnimal(Animal animal);
    }
}


