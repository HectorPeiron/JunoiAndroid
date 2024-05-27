package com.tfgjunio.presenter.Crianza;

import com.tfgjunio.contract.Crianza.ModifyCrianzaContract;
import com.tfgjunio.domain.Crianza;

public class ModifyCrianzaPresenter implements ModifyCrianzaContract.Presenter {
    private ModifyCrianzaContract.View view;
    private ModifyCrianzaContract.Model model;

    public ModifyCrianzaPresenter(ModifyCrianzaContract.View view, ModifyCrianzaContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void modifyCrianza(long id, Crianza crianza) {
        model.modifyCrianza(id, crianza, new ModifyCrianzaContract.Model.OnCrianzaModifiedListener() {
            @Override
            public void onCrianzaModified(Crianza updatedCrianza) {
                view.onCrianzaModified(updatedCrianza);
            }

            @Override
            public void onModificationError(String error) {
                view.showError(error);
            }
        });
    }
}
