package com.tfgjunio.view.Baja;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tfgjunio.R;
import com.tfgjunio.contract.Baja.AddBajaContract;
import com.tfgjunio.domain.Baja;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.TipoBaja;
import com.tfgjunio.model.Baja.AddBajaModel;
import com.tfgjunio.presenter.Baja.AddBajaPresenter;
import com.tfgjunio.utils.PreferencesHelper;
import com.tfgjunio.view.Animal.AddAnimalView;
import com.tfgjunio.view.Crianza.AddCrianzaView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class AddBajaView extends AppCompatActivity implements AddBajaContract.View {

    private EditText etFechaBaja, etCantidad;
    private Spinner spinnerTipoBaja;
    private Button btnGuardarBaja;

    private Button btnVolver;
    private Button btnVerGraficas;
    private AddBajaPresenter presenter;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baja);

        preferencesHelper = new PreferencesHelper(this);
        presenter = new AddBajaPresenter(this, new AddBajaModel());

        setupViews();
        presenter.loadTipoBajas();
    }

    private void setupViews() {
        etFechaBaja = findViewById(R.id.etFechaBaja);
        etCantidad = findViewById(R.id.etCantidad);
        spinnerTipoBaja = findViewById(R.id.spinnerTipoBaja);
        btnGuardarBaja = findViewById(R.id.btnGuardarBaja);
        btnVerGraficas = findViewById(R.id.btnVerGraficas);
        etFechaBaja.setText(LocalDate.now().toString());
        etFechaBaja.setOnClickListener(v -> showDatePicker());
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(v -> {
            onBackPressed();

        });
        btnGuardarBaja.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                LocalDate fecha = LocalDate.parse(etFechaBaja.getText().toString());
                int cantidad = Integer.parseInt(etCantidad.getText().toString());
                TipoBaja tipoBaja = (TipoBaja) spinnerTipoBaja.getSelectedItem();
                Crianza crianza = new Crianza();
                crianza.setId(crianzaId);

                Baja baja = new Baja(fecha, cantidad, tipoBaja, crianza);
                presenter.addBaja(baja);
            } else {
                Toast.makeText(this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            }
        });

        btnVerGraficas.setOnClickListener(v -> {
            Intent intent = new Intent(AddBajaView.this, GraficosView.class);
            startActivity(intent);
        });
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) ->
                        etFechaBaja.setText(LocalDate.of(year1, monthOfYear + 1, dayOfMonth).toString()),
                year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void showTipoBajas(List<TipoBaja> tipoBajas) {
        ArrayAdapter<TipoBaja> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoBajas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoBaja.setAdapter(adapter);
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
    public void onBajaAdded() {
        Toast.makeText(this, "Baja registrada correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
