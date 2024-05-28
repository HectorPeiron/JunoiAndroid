package com.tfgjunio.presenter.Baja;

import com.tfgjunio.contract.Baja.AddBajaContract;
import com.tfgjunio.domain.Baja;
import com.tfgjunio.domain.TipoBaja;

import java.util.List;

public class AddBajaPresenter implements AddBajaContract.Presenter {
    private AddBajaContract.View view;
    private AddBajaContract.Model model;

    public AddBajaPresenter(AddBajaContract.View view, AddBajaContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadTipoBajas() {
        model.loadTipoBajas(new AddBajaContract.Model.OnTipoBajasLoadedListener() {
            @Override
            public void onTipoBajasLoaded(List<TipoBaja> tipoBajas) {
                view.showTipoBajas(tipoBajas);
            }

            @Override
            public void onLoadError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void addBaja(Baja baja) {
        model.addBaja(baja, new AddBajaContract.Model.OnBajaAddedListener() {
            @Override
            public void onBajaAdded() {
                view.onBajaAdded();
            }

            @Override
            public void onAddError(String message) {
                view.showError(message);
            }
        });
    }
}
