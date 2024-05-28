package com.tfgjunio.contract.Compra;

import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Recurso;

import java.util.List;

public interface AddCompraContract {
    interface View {
        void onCompraAdded();
        void showRecursos(List<Recurso> recursos);
        void showError(String message);
        void showMessage(String message);
    }

    interface Presenter {
        void addCompra(Compra compra);
        void loadRecursos();
    }

    interface Model {
        interface OnCompraAddedListener {
            void onCompraAdded();
            void onAddError(String message);
        }

        interface OnRecursosLoadedListener {
            void onRecursosLoaded(List<Recurso> recursos);
            void onLoadError(String message);
        }

        void addCompra(Compra compra, OnCompraAddedListener listener);
        void loadRecursos(OnRecursosLoadedListener listener);
    }
}
