package com.tfgjunio.presenter.Compra;

import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Recurso;

import java.util.List;

public class AddCompraPresenter implements AddCompraContract.Presenter {
    private final AddCompraContract.View view;
    private final AddCompraContract.Model model;

    public AddCompraPresenter(AddCompraContract.View view, AddCompraContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void addCompra(Compra compra) {
        model.addCompra(compra, new AddCompraContract.Model.OnCompraAddedListener() {
            @Override
            public void onCompraAdded() {
                view.onCompraAdded();
            }

            @Override
            public void onAddError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void loadRecursos() {
        model.loadRecursos(new AddCompraContract.Model.OnRecursosLoadedListener() {
            @Override
            public void onRecursosLoaded(List<Recurso> recursos) {
                view.showRecursos(recursos);
            }

            @Override
            public void onLoadError(String message) {
                view.showError(message);
            }
        });
    }
}
