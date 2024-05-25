package com.tfgjunio.presenter.Recurso;


import com.tfgjunio.contract.Recurso.RecursoListContract;
import com.tfgjunio.domain.Recurso;
import com.tfgjunio.model.Recurso.RecursoListModel;

import java.util.List;

public class RecursoListPresenter implements RecursoListContract.Presenter, RecursoListContract.Model.OnLoadRecursoListener {

    private RecursoListModel model;
    private RecursoListView view;

    public RecursoListPresenter(RecursoListView view) {
        this.view = view;
        this.model = new RecursoListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllRecursos() {
        model.loadAllRecursos(this);
    }

    @Override
    public void onLoadRecursosSuccess(List<Recurso> recursos) {
        view.showRecursos(recursos);

    }

    @Override
    public void onLoadRecursosError(String message) {
        view.showMessage(message);

    }
}