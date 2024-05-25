package com.tfgjunio.presenter.Veterinario;

import com.tfgjunio.contract.Veterinario.ModifyVeterinarioContract;
import com.tfgjunio.domain.Veterinario;
import com.tfgjunio.model.Veterinario.ModifyVeterinarioModel;

public class ModifyVeterinarioPresenter implements ModifyVeterinarioContract.Presenter, ModifyVeterinarioContract.Model.OnModifyVeterinarioListener {

    private ModifyVeterinarioModel model;
    private ModifyVeterinarioView view;

    public ModifyVeterinarioPresenter(ModifyVeterinarioView view) {
        this.model = new ModifyVeterinarioModel();
        this.view = view;
    }

    @Override
    public void modifyVeterinario(long id, Veterinario veterinario) {
        model.modifyVeterinario(id, veterinario, this);
    }

    @Override
    public void onModifySuccess(Veterinario veterinario) {
        view.showMessage("Veterinario modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Veterinario");

    }
}
