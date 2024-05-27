package com.tfgjunio.presenter.Animal;

import com.tfgjunio.contract.Animal.AddAnimalContract;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.domain.TipoAnimal;

import java.util.List;

public class AddAnimalPresenter implements AddAnimalContract.Presenter {

    private AddAnimalContract.View view;
    private AddAnimalContract.Model model;

    public AddAnimalPresenter(AddAnimalContract.View view, AddAnimalContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadTipoAnimales() {
        model.loadTipoAnimales(new AddAnimalContract.Model.OnTipoAnimalesLoadedListener() {
            @Override
            public void onTipoAnimalesLoaded(List<TipoAnimal> tipoAnimales) {
                view.showTipoAnimales(tipoAnimales);
            }

            @Override
            public void onLoadError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void addAnimal(Animal animal) {
        model.addAnimal(animal, new AddAnimalContract.Model.OnAnimalAddedListener() {
            @Override
            public void onAnimalAdded(Animal animal) {
                view.onAnimalAdded(animal);
            }

            @Override
            public void onAddError(String message) {
                view.showError(message);
            }
        });
    }
}
