package com.tfgjunio.view.Compra;

import android.os.Bundle;
import android.view.View;
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
import com.tfgjunio.model.Compra.AddCompraModel;
import com.tfgjunio.presenter.Compra.AddCompraPresenter;
import com.tfgjunio.utils.PreferencesHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCompraView extends AppCompatActivity implements AddCompraContract.View {

    private EditText etDescripcion;
    private Spinner spinnerRecurso;
    private Button btnGuardarCompra;
    private PreferencesHelper preferencesHelper;
    private AddCompraPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_compra);

        preferencesHelper = new PreferencesHelper(this);
        presenter = new AddCompraPresenter(this, new AddCompraModel());

        etDescripcion = findViewById(R.id.etDescripcionCompra);
        spinnerRecurso = findViewById(R.id.spinnerRecurso);
        btnGuardarCompra = findViewById(R.id.btnGuardarCompra);

        btnGuardarCompra.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                LocalDate fechaCompra = LocalDate.now();
                String descripcion = etDescripcion.getText().toString();
                Recurso recursoSeleccionado = (Recurso) spinnerRecurso.getSelectedItem();
                List<Recurso> recursos = new ArrayList<>();
                recursos.add(recursoSeleccionado);
                Crianza crianza = new Crianza();
                crianza.setId(crianzaId);

                Compra compra = new Compra(fechaCompra, descripcion, recursos, crianza);
                presenter.addCompra(compra);
            } else {
                Toast.makeText(AddCompraView.this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            }
        });

        // Cargar recursos aquí, similar a cargar tipos de animales
        presenter.loadRecursos();
    }

    @Override
    public void showRecursos(List<Recurso> recursos) {
        ArrayAdapter<Recurso> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, recursos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecurso.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompraAdded() {
        Toast.makeText(this, "Compra añadida correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
