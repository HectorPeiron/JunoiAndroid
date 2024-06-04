package com.tfgjunio.view.Compra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tfgjunio.R;
import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.Recurso;
import com.tfgjunio.model.Compra.AddCompraModel;
import com.tfgjunio.presenter.Compra.AddCompraPresenter;
import com.tfgjunio.utils.PreferencesHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCompraView extends AppCompatActivity implements AddCompraContract.View {
    private EditText etDescripcion;
    private LinearLayout spinnerContainer;
    private Button btnAgregarRecurso, btnGuardarCompra, btnVerCompras, btnVolver;
    private AddCompraPresenter presenter;
    private PreferencesHelper preferencesHelper;
    private List<Spinner> spinnersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_compra);

        preferencesHelper = new PreferencesHelper(this);
        presenter = new AddCompraPresenter(this, new AddCompraModel());

        setupViews();
        presenter.loadRecursos(); // Carga los recursos disponibles para la compra
    }

    private void setupViews() {
        etDescripcion = findViewById(R.id.etDescripcionCompra);
        spinnerContainer = findViewById(R.id.spinnerContainer);
        btnAgregarRecurso = findViewById(R.id.btnAgregarRecurso);
        btnGuardarCompra = findViewById(R.id.btnGuardarCompra);
        btnVerCompras = findViewById(R.id.btnVerCompras);
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(v -> {
            onBackPressed();

        });

        // Añadir el spinner inicial a la lista
        spinnersList.add(findViewById(R.id.spinnerRecurso));

        btnVerCompras.setOnClickListener(v -> {
            Intent intent = new Intent(AddCompraView.this, CompraListView.class);
            startActivity(intent);
        });

        btnGuardarCompra.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                LocalDate fechaCompra = LocalDate.now();
                String descripcion = etDescripcion.getText().toString();
                List<Recurso> recursos = new ArrayList<>();
                for (Spinner spinner : spinnersList) {
                    Recurso recursoSeleccionado = (Recurso) spinner.getSelectedItem();
                    recursos.add(recursoSeleccionado);
                }
                Crianza crianza = new Crianza();
                crianza.setId(crianzaId);

                Compra compra = new Compra(fechaCompra, descripcion, recursos, crianza);
                presenter.addCompra(compra);
            } else {
                Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            }
        });

        btnAgregarRecurso.setOnClickListener(v -> agregarNuevoSpinner());
    }

    private void agregarNuevoSpinner() {
        Spinner nuevoSpinner = new Spinner(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 16, 0, 0);
        nuevoSpinner.setLayoutParams(layoutParams);

        spinnerContainer.addView(nuevoSpinner);
        spinnersList.add(nuevoSpinner);

        presenter.loadRecursosParaSpinner(nuevoSpinner);
    }

    @Override
    public void showRecursos(List<Recurso> recursos) {
        ArrayAdapter<Recurso> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, recursos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (Spinner spinner : spinnersList) {
            spinner.setAdapter(adapter);
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompraAdded() {
        Toast.makeText(this, "Compra registrada correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
