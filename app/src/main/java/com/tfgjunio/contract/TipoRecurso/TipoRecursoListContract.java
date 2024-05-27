package com.tfgjunio.contract.TipoRecurso;

import com.tfgjunio.domain.TipoRecurso;

import java.util.List;

public interface TipoRecursoListContract {
    interface Model {
        interface OnLoadTipoRecursoListener {
            void onLoadTipoRecursosSuccess(List<TipoRecurso> tipoRecursos);

            void onLoadTipoRecursosError(String message);
        }

        void loadAllTipoRecursos(OnLoadTipoRecursoListener listener);
    }

    interface View {
        void showTipoRecursos(List<TipoRecurso> tipoRecursos);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllTipoRecursos();
    }
}
