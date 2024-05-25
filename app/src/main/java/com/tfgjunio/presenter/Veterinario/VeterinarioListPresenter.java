package com.tfgjunio.presenter.Veterinario;


import com.tfgjunio.contract.Veterinario.VeterinarioListContract;
import com.tfgjunio.domain.Veterinario;
import com.tfgjunio.model.Veterinario.VeterinarioListModel;

import java.util.List;

public class VeterinarioListPresenter  implements VeterinarioListContract.Presenter, VeterinarioListContract.Model.OnLoadVeterinarioListener {

    private VeterinarioListModel model;
    private VeterinarioListView view;

    public VeterinarioListPresenter(VeterinarioListView view) {
        this.view = view;
        this.model = new VeterinarioListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllVeterinarios() {
        model.loadAllVeterinarios(this);
    }

    @Override
    public void onLoadVeterinariosSuccess(List<Veterinario> veterinarios) {
        view.showVeterinarios(veterinarios);

    }

    @Override
    public void onLoadVeterinariosError(String message) {
        view.showMessage(message);

    }
}