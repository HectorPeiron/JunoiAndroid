package com.tfgjunio.view.Crianza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tfgjunio.R;
import com.tfgjunio.contract.Crianza.AddCrianzaContract;
import com.tfgjunio.contract.Crianza.ModifyCrianzaContract;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.model.Crianza.AddCrianzaModel;
import com.tfgjunio.model.Crianza.ModifyCrianzaModel;
import com.tfgjunio.presenter.Crianza.AddCrianzaPresenter;
import com.tfgjunio.presenter.Crianza.ModifyCrianzaPresenter;
import com.tfgjunio.utils.PreferencesHelper;
import com.tfgjunio.view.Animal.AddAnimalView;
import com.tfgjunio.view.Baja.AddBajaView;
import com.tfgjunio.view.Compra.AddCompraView;

import java.time.LocalDate;

public class AddCrianzaView extends AppCompatActivity implements AddCrianzaContract.View, ModifyCrianzaContract.View {

    private Button btnGuardarCrianza;
    private Button btnCrianzaAnimal;
    private Button btnCrianzaCompra;
    private Button btnCrianzaBajas;
    private Button btnCrianzasAnteriores;
    private Button btnSoporte;
    private Button btnFinalizarCrianza;

    private AddCrianzaPresenter addCrianzaPresenter;
    private ModifyCrianzaPresenter modifyCrianzaPresenter;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesHelper = new PreferencesHelper(this);
        addCrianzaPresenter = new AddCrianzaPresenter(this, new AddCrianzaModel());
        modifyCrianzaPresenter = new ModifyCrianzaPresenter(this, new ModifyCrianzaModel());

        setupButtons();
    }

    private void setupButtons() {
        btnGuardarCrianza = findViewById(R.id.btnGuardarCrianza);
        btnCrianzaAnimal = findViewById(R.id.btnCrianzaAnimal);
        btnCrianzaCompra = findViewById(R.id.btnCrianzaCompra);
        btnCrianzaBajas = findViewById(R.id.btnCrianzaBajas);
        btnCrianzasAnteriores = findViewById(R.id.btnCrianzasAnteriores);
        btnSoporte = findViewById(R.id.btnSoporte);
        btnFinalizarCrianza = findViewById(R.id.btnFinalizarCrianza);

        btnGuardarCrianza.setOnClickListener(v -> {
            LocalDate fechaInicio = LocalDate.now();
            Crianza crianza = new Crianza(fechaInicio);
            addCrianzaPresenter.addCrianza(crianza);
        });



        btnFinalizarCrianza.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                String fechaInicioStr = preferencesHelper.getCrianzaFechaInicio();
                if(fechaInicioStr != null) {
                    LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
                    LocalDate fechaFin = LocalDate.now();
                    Crianza crianza = new Crianza(crianzaId, fechaInicio, fechaFin, null, null, null);
                    modifyCrianzaPresenter.modifyCrianza(crianzaId, crianza);
                } else {
                    Toast.makeText(AddCrianzaView.this, "Fecha de inicio no disponible", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddCrianzaView.this, "No hay crianza activa para finalizar", Toast.LENGTH_SHORT).show();
            }
        });
        btnCrianzaAnimal.setOnClickListener(v -> {
            Intent intent = new Intent(AddCrianzaView.this, AddAnimalView.class);
            startActivity(intent);
        });
        btnCrianzaCompra.setOnClickListener(v -> {
            Intent intent = new Intent(AddCrianzaView.this, AddCompraView.class);
            startActivity(intent);
        });
        btnCrianzaBajas.setOnClickListener(v -> {
            Intent intent = new Intent(AddCrianzaView.this, AddBajaView.class);
            startActivity(intent);
        });


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
    public void onCrianzaAdded(Crianza crianza) {
        preferencesHelper.saveCrianzaId(crianza.getId());
        preferencesHelper.saveCrianzaFechaInicio(crianza.getFechaInicio().toString());
        showManagementButtons(true);
        Toast.makeText(this, "Crianza creada correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCrianzaModified(Crianza crianza) {
        preferencesHelper.clearCrianzaId();
        preferencesHelper.clearCrianzaFechaInicio();
        showManagementButtons(false);
        Toast.makeText(this, "Crianza finalizada correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showManagementButtons(boolean visible) {
        int visibility = visible ? View.VISIBLE : View.GONE;
        btnCrianzaAnimal.setVisibility(visibility);
        btnCrianzaCompra.setVisibility(visibility);
        btnCrianzaBajas.setVisibility(visibility);
        btnCrianzasAnteriores.setVisibility(visibility);
        btnSoporte.setVisibility(visibility);
        btnFinalizarCrianza.setVisibility(visibility);
        btnGuardarCrianza.setVisibility(visible ? View.GONE : View.VISIBLE);
    }
}
