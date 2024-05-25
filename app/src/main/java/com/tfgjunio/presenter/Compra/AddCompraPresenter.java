package com.tfgjunio.presenter.Compra;

import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.model.Compra.AddCompraModel;
import com.tfgjunio.view.Compra.AddCompraView;

public class AddCompraPresenter implements AddCompraContract.Presenter, AddCompraContract.Model.OnRegisterCompraListener {
    private AddCompraModel model;
    private AddCompraView view;

    public AddCompraPresenter(AddCompraView view) {
        this.model = new AddCompraModel();
        this.view = view;
    }

    @Override
    public void addCompra(Compra compra) {
        model.addCompra(compra, this);
    }


    @Override
    public void onRegisterSuccess(Compra compra) {
        view.showMessage(" Compra " + compra.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Compra");

    }
}

