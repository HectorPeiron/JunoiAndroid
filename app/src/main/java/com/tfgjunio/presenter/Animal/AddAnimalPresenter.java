package com.tfgjunio.presenter.Animal;

import com.tfgjunio.contract.Animal.AddAnimalContract;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.model.Animal.AddAnimalModel;

public class AddAnimalPresenter implements AddAnimalContract.Presenter, AddAnimalContract.Model.OnRegisterAnimalListener {
    private AddAnimalModel model;
    private AddAnimalView view;

    public AddAnimalPresenter(AddAnimalView view) {
        this.model = new AddAnimalModel();
        this.view = view;
    }

    @Override
    public void addAnimal(Animal animal) {
        model.addAnimal(animal, this);
    }


    @Override
    public void onRegisterSuccess(Animal animal) {
        view.showMessage("El Animal " + animal.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Animal");

    }
}

