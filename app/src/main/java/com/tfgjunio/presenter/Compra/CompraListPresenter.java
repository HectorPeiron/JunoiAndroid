package com.tfgjunio.presenter.Compra;

import com.tfgjunio.contract.Compra.CompraListContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.model.Compra.CompraListModel;

import java.util.List;

public class CompraListPresenter  implements CompraListContract.Presenter, CompraListContract.Model.OnLoadCompraListener {

    private CompraListModel model;
    private CompraListView view;

    public CompraListPresenter(CompraListView view) {
        this.view = view;
        this.model = new CompraListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllCompras() {
        model.loadAllCompras(this);
    }

    @Override
    public void onLoadComprasSuccess(List<Compra> compras) {
        view.showCompras(compras);

    }

    @Override
    public void onLoadComprasError(String message) {
        view.showMessage(message);

    }
}