package com.tfgjunio.presenter.Crianza;


import com.tfgjunio.contract.Crianza.ModifyCrianzaContract;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.model.Crianza.ModifyCrianzaModel;

public class ModifyCrianzaPresenter implements ModifyCrianzaContract.Presenter, ModifyCrianzaContract.Model.OnModifyCrianzaListener {

    private ModifyCrianzaModel model;
    private ModifyCrianzaView view;

    public ModifyCrianzaPresenter(ModifyCrianzaView view) {
        this.model = new ModifyCrianzaModel();
        this.view = view;
    }

    @Override
    public void modifyCrianza(long id, Crianza crianza) {
        model.modifyCrianza(id, crianza, this);
    }

    @Override
    public void onModifySuccess(Crianza crianza) {
        view.showMessage("Crianza modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Crianza");
    }
}