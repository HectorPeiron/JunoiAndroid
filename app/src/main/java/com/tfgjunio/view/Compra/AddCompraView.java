package com.tfgjunio.view.Compra;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.tfgjunio.api.JunioAPI;
import com.tfgjunio.api.JunioApiInterface;
import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.Recurso;
import com.tfgjunio.presenter.Compra.AddCompraPresenter;
import com.tfgjunio.model.AddCompraModel;
import com.tfgjunio.model.CompraListModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCompraView extends AppCompatActivity implements AddCompraContract.View {
    private AddCompraPresenter presenter;

    private EditText etFechaCompra;
    private EditText etDescripcionCompra;
    private Spinner spinnerRecursos;

    private List<Recurso> recursoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_compra_view);

        etFechaCompra = findViewById(R.id.etFechaCompra);
        etDescripcionCompra = findViewById(R.id.etDescripcionCompra);
        spinnerRecursos = findViewById(R.id.spinnerRecursos);
        Button btnAddCompra = findViewById(R.id.btnAddCompra);
        Button btnListCompra = findViewById(R.id.btnListCompra);

        presenter = new AddCompraPresenter(this);

        /**  PONER FECHA ACTUAL EN FECHA COMPRA */

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        etFechaCompra.setText(currentDate.format(formatter));

        /**  DATOS DE LA API */
        loadRecursosFromAPI();

        btnAddCompra.setOnClickListener(v -> addCompra());
        btnListCompra.setOnClickListener(v -> viewPedidos());
    }

    private void loadRecursosFromAPI() {
        JunioApiInterface junioApiInterface = JunioAPI.buildInstance();
        Call<List<Recurso>> call = junioApiInterface.getRecursos();
        call.enqueue(new Callback<List<Recurso>>() {
            @Override
            public void onResponse(Call<List<Recurso>> call, Response<List<Recurso>> response) {
                if (response.isSuccessful()) {
                    recursoList = response.body();
                    List<String> recursoNombres = new ArrayList<>();
                    for (Recurso recurso : recursoList) {
                        recursoNombres.add(recurso.getNombre());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddCompraView.this, android.R.layout.simple_spinner_item, recursoNombres);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRecursos.setAdapter(adapter);
                } else {
                    showError("Failed to load resources");
                }
            }

            @Override
            public void onFailure(Call<List<Recurso>> call, Throwable t) {
                showError("Failed to load resources: " + t.getMessage());
                Log.e("AddCompraView", t.getMessage(), t);
            }
        });
    }

    private void addCompra() {
        String fechaCompraStr = etFechaCompra.getText().toString();
        LocalDate fechaCompra = LocalDate.parse(fechaCompraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String descripcionCompra = etDescripcionCompra.getText().toString();

        /**  DESPLEGABLE  **/
        String selectedRecursoName = spinnerRecursos.getSelectedItem().toString();
        Recurso selectedRecurso = getRecursoByName(selectedRecursoName);

        if (selectedRecurso != null) {
            List<Recurso> recursos = new ArrayList<>();
            recursos.add(selectedRecurso);

            /**  AQUI DETECTO LA CRIANZA ACTIVA PERO NO SE COMO HACERLO  **/
            Crianza crianza = detectCrianza();

            Compra compra = new Compra(null, fechaCompra, descripcionCompra, recursos, crianza);
            presenter.addCompra(compra);
        } else {
            showError("Selected resource not found");
        }
    }

    private Recurso getRecursoByName(String name) {
        for (Recurso recurso : recursoList) {
            if (recurso.getNombre().equals(name)) {
                return recurso;
            }
        }
        return null;
    }


    /**  ********************************************************************/
    /**        NO SE COMO HACER QUE SE SELECCIONE LA CRIANZA ACTUAL         */
    /** *********************************************************************/


    private Crianza detectCrianza() {
        // LOGICA PARA SABER CUAL ES LA CRIANZA ACTUAL
        return new Crianza("Detected Crianza");
    }

    private void viewPedidos() {
        /**  VER EL HOLDER */
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(etFechaCompra, errorMessage, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(etFechaCompra, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        etFechaCompra.setText(currentDate.format(formatter));
        etDescripcionCompra.setText("");
        spinnerRecursos.setSelection(0);
        etFechaCompra.requestFocus();
    }
}
