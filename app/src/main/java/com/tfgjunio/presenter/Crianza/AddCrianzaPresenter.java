package com.tfgjunio.presenter.Crianza;


import com.tfgjunio.contract.Crianza.AddCrianzaContract;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.model.Crianza.AddCrianzaModel;

public class AddCrianzaPresenter implements AddCrianzaContract.Presenter, AddCrianzaContract.Model.OnRegisterCrianzaListener {
    private AddCrianzaModel model;
    private AddCrianzaView view;

    public AddCrianzaPresenter(AddCrianzaView view) {
        this.model = new AddCrianzaModel();
        this.view = view;
    }

    @Override
    public void addCrianza(Crianza crianza) {
        model.addCrianza(crianza, this);
    }


    @Override
    public void onRegisterSuccess(Crianza crianza) {
        view.showMessage("El Crianza " + crianza.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Crianza");

    }
}

