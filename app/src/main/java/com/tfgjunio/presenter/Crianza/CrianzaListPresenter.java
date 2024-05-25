package com.tfgjunio.presenter.Crianza;

import com.tfgjunio.contract.Crianza.CrianzaListContract;

import java.util.List;

public class CrianzaListPresenter implements CrianzaListContract.Presenter, CrianzaListContract.Model.OnLoadCrianzaListener {

    private CrianzaListModel model;
    private CrianzaListView view;

    public CrianzaListPresenter(CrianzaListView view) {
        this.view = view;
        this.model = new CrianzaListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllCrianzas() {
        model.loadAllCrianzas(this);
    }

    @Override
    public void onLoadCrianzasSuccess(List<Crianza> crianzas) {
        view.showCrianzas(crianzas);

    }

    @Override
    public void onLoadCrianzasError(String message) {
        view.showMessage(message);

    }
}