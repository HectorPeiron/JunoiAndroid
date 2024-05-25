package com.tfgjunio.presenter.Animal;

import com.tfgjunio.domain.Animal;
import com.tfgjunio.model.Animal.AnimalListModel;

import java.util.List;

public class AnimalListPresenter implements AnimalListContract.Presenter, AnimalListContract.Model.OnLoadAnimalListener {

    private AnimalListModel model;
    private AnimalListView view;

    public AnimalListPresenter(AnimalListView view) {
        this.view = view;
        this.model = new AnimalListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllAnimales() {
        model.loadAllAnimales(this);
    }

    @Override
    public void onLoadAnimalesSuccess(List<Animal> animales) {
        view.showAnimales(animales);

    }

    @Override
    public void onLoadAnimalesError(String message) {
        view.showMessage(message);

    }
}