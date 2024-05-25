package com.tfgjunio.presenter.Animal;

import com.tfgjunio.contract.Animal.ModifyAnimalContract;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.model.Animal.ModifyAnimalModel;

public  class ModifyAnimalPresenter implements ModifyAnimalContract.Presenter, ModifyAnimalContract.Model.OnModifyAnimalListener {

    private ModifyAnimalModel model;
    private ModifyAnimalView view;

    public ModifyAnimalPresenter(ModifyAnimalView view) {
        this.model = new ModifyAnimalModel();
        this.view = view;
    }

    @Override
    public void modifyAnimal(long id, Animal animal) {
        model.modifyAnimal(id, animal, this);
    }

    @Override
    public void onModifySuccess(Animal animal) {
        view.showMessage("Animal modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Animal");

    }
}
