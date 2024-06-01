package com.tfgjunio.view.Compra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tfgjunio.R;
import com.tfgjunio.contract.Compra.AddCompraContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.Recurso;
import com.tfgjunio.domain.TipoAnimal;
import com.tfgjunio.model.Compra.AddCompraModel;
import com.tfgjunio.presenter.Compra.AddCompraPresenter;
import com.tfgjunio.utils.PreferencesHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCompraView extends AppCompatActivity implements AddCompraContract.View {
    private EditText etDescripcion;
    private Spinner spinnerRecurso;
    private Button btnGuardarCompra, btnVerCompras;
    private AddCompraPresenter presenter;
    private PreferencesHelper preferencesHelper;

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
        spinnerRecurso = findViewById(R.id.spinnerRecurso);
        btnGuardarCompra = findViewById(R.id.btnGuardarCompra);
        btnVerCompras = findViewById(R.id.btnVerCompras);

        btnVerCompras.setOnClickListener(v -> {
            Intent intent = new Intent(AddCompraView.this, CompraListView.class);
            startActivity(intent);
        });

        btnGuardarCompra.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                LocalDate fechaCompra = LocalDate.now();  // or parse from etFechaCompra if set by the user
                String descripcion = etDescripcion.getText().toString();
                Recurso recursoSeleccionado = (Recurso) spinnerRecurso.getSelectedItem();
                List<Recurso> recursos = new ArrayList<>();
                recursos.add(recursoSeleccionado);
                Crianza crianza = new Crianza();
                crianza.setId(crianzaId);

                Compra compra = new Compra(fechaCompra, descripcion, recursos, crianza);
                presenter.addCompra(compra);
            } else {
                Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showRecursos(List<Recurso> recursos) {
        ArrayAdapter<Recurso> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, recursos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecurso.setAdapter(adapter);
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
        finish(); // Cerrar la actividad una vez que la compra es agregada correctamente
    }
}
