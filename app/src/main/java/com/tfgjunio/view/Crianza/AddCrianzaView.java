package com.tfgjunio.view.Crianza;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.tfgjunio.utils.DialogDismissListener;
import com.tfgjunio.utils.InfoDialogFragment;
import com.tfgjunio.utils.PreferencesHelper;
import com.tfgjunio.view.Animal.AddAnimalView;
import com.tfgjunio.view.Baja.AddBajaView;
import com.tfgjunio.view.Compra.AddCompraView;
import com.tfgjunio.view.CorreoView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCrianzaView extends AppCompatActivity implements AddCrianzaContract.View, ModifyCrianzaContract.View, DialogDismissListener {

    private Button btnGuardarCrianza;
    private Button btnCrianzaAnimal;
    private Button btnCrianzaCompra;
    private Button btnCrianzaBajas;
    private Button btnCrianzasAnteriores;
    private Button btnSoporte;
    private Button btnVerCrianza;
    private Button btnFinalizarCrianza;
    private ImageView imgBackground;
    private ImageView principal;

    private AddCrianzaPresenter addCrianzaPresenter;
    private ModifyCrianzaPresenter modifyCrianzaPresenter;
    private PreferencesHelper preferencesHelper;

    private List<String> infoMessages;
    private int currentMessageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesHelper = new PreferencesHelper(this);
        addCrianzaPresenter = new AddCrianzaPresenter(this, new AddCrianzaModel());
        modifyCrianzaPresenter = new ModifyCrianzaPresenter(this, new ModifyCrianzaModel());

        setupButtons();
        checkActiveCrianza();
        setupInfoMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_info) {
            currentMessageIndex = 0;
            showNextInfoDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupInfoMessages() {
        infoMessages = new ArrayList<>();
        infoMessages.add(getString(R.string.info_message_1));
        infoMessages.add(getString(R.string.info_message_2));
        infoMessages.add(getString(R.string.info_message_3));
        infoMessages.add(getString(R.string.info_message_4));
        infoMessages.add(getString(R.string.info_message_5));
        infoMessages.add(getString(R.string.info_message_6));
    }

    public void showNextInfoDialog() {
        if (currentMessageIndex < infoMessages.size()) {
            InfoDialogFragment dialogFragment = InfoDialogFragment.newInstance(infoMessages.get(currentMessageIndex), this);
            dialogFragment.show(getSupportFragmentManager(), "infoDialog");
            currentMessageIndex++;
        }
    }

    @Override
    public void onDialogDismiss() {
        showNextInfoDialog();
    }

    private void setupButtons() {
        btnGuardarCrianza = findViewById(R.id.btnGuardarCrianza);
        btnCrianzaAnimal = findViewById(R.id.btnCrianzaAnimal);
        btnCrianzaCompra = findViewById(R.id.btnCrianzaCompra);
        btnCrianzaBajas = findViewById(R.id.btnCrianzaBajas);
        btnCrianzasAnteriores = findViewById(R.id.btnCrianzasAnteriores);
        btnSoporte = findViewById(R.id.btnSoporte);
        btnFinalizarCrianza = findViewById(R.id.btnFinalizarCrianza);
        btnVerCrianza = findViewById(R.id.btnVerCrianza);
        imgBackground = findViewById(R.id.imgBackground);
        principal = findViewById(R.id.principal);

        btnGuardarCrianza.setOnClickListener(v -> {
            LocalDate fechaInicio = LocalDate.now();
            Crianza crianza = new Crianza(fechaInicio);
            addCrianzaPresenter.addCrianza(crianza);
        });

        btnFinalizarCrianza.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                String fechaInicioStr = preferencesHelper.getCrianzaFechaInicio();
                if (fechaInicioStr != null) {
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

        btnVerCrianza.setOnClickListener(v -> {
            Intent intent = new Intent(AddCrianzaView.this, CrianzaDetailView.class);
            startActivity(intent);
        });

        btnCrianzasAnteriores.setOnClickListener(v -> {
            Intent intent = new Intent(AddCrianzaView.this, CrianzaTodasListView.class);
            startActivity(intent);
        });

        btnSoporte.setOnClickListener(v -> {
            Intent intent = new Intent(AddCrianzaView.this, CorreoView.class);
            startActivity(intent);
        });
    }

    private void checkActiveCrianza() {
        long crianzaId = preferencesHelper.getCrianzaId();
        if (crianzaId != -1) {
            imgBackground.setVisibility(View.GONE);
            principal.setVisibility(View.VISIBLE);
            showManagementButtons(true);
        } else {
            imgBackground.setVisibility(View.VISIBLE);
            principal.setVisibility(View.GONE);
            showManagementButtons(false);
        }
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
        imgBackground.setVisibility(View.GONE);
        principal.setVisibility(View.VISIBLE);
        showManagementButtons(true);
        Toast.makeText(this, "Crianza creada correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCrianzaModified(Crianza crianza) {
        preferencesHelper.clearCrianzaId();
        preferencesHelper.clearCrianzaFechaInicio();
        imgBackground.setVisibility(View.VISIBLE);
        principal.setVisibility(View.GONE);
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
        btnVerCrianza.setVisibility(visibility);
        btnFinalizarCrianza.setVisibility(visibility);
        btnGuardarCrianza.setVisibility(visible ? View.GONE : View.VISIBLE);
    }
}
