package com.tfgjunio.contract.Compra;

import com.tfgjunio.domain.Compra;

import java.util.List;

public interface CompraListContract {
    interface Model {
        interface OnLoadCompraListener {
            void onLoadComprasSuccess(List<Compra> compras);

            void onLoadComprasError(String message);
        }

        void loadAllCompras(CompraListContract.Model.OnLoadCompraListener listener);
    }

    interface View {
        void showCompras(List<Compra> compras);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllCompras();
    }

}
