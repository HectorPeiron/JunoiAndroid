package com.tfgjunio.presenter.Crianza;

import com.tfgjunio.contract.Crianza.AddCrianzaContract;
import com.tfgjunio.domain.Crianza;

public class AddCrianzaPresenter implements AddCrianzaContract.Presenter {
    private AddCrianzaContract.View view;
    private AddCrianzaContract.Model model;

    public AddCrianzaPresenter(AddCrianzaContract.View view, AddCrianzaContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void addCrianza(Crianza crianza) {
        model.addCrianza(crianza, new AddCrianzaContract.Model.OnRegisterCrianzaListener() {
            @Override
            public void onRegisterSuccess(Crianza crianza) {
                view.onCrianzaAdded(crianza);
            }

            @Override
            public void onRegisterError(String error) {
                view.showError(error);
            }
        });
    }
}
