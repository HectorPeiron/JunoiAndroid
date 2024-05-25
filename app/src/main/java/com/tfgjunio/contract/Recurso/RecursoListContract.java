package com.tfgjunio.contract.Recurso;

import com.tfgjunio.contract.Crianza.CrianzaListContract;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.Recurso;

import java.util.List;

public interface RecursoListContract {

    interface Model {
        interface OnLoadRecursoListener {
            void onLoadRecursosSuccess(List<Recurso> recursos);

            void onLoadRecursosError(String message);
        }

        void loadAllRecursos(RecursoListContract.Model.OnLoadRecursoListener listener);
    }

    interface View {
        void showRecursos(List<Recurso> recursos);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllRecursos();
    }

}
