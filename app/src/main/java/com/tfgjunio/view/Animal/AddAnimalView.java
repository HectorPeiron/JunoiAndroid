package com.tfgjunio.view.Animal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tfgjunio.R;
import com.tfgjunio.contract.Animal.AddAnimalContract;
import com.tfgjunio.domain.Animal;
import com.tfgjunio.domain.Crianza;
import com.tfgjunio.domain.TipoAnimal;
import com.tfgjunio.model.Animal.AddAnimalModel;
import com.tfgjunio.presenter.Animal.AddAnimalPresenter;
import com.tfgjunio.utils.PreferencesHelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AddAnimalView extends AppCompatActivity implements AddAnimalContract.View {

    private EditText etNumero;
    private EditText etSexo;
    private EditText etPeso;


    private Spinner spinnerTipoAnimal;
    private Button btnGuardarAnimal;
    private Button btnVerAnimales;

    private Button btnVolver;
    private AddAnimalPresenter presenter;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        preferencesHelper = new PreferencesHelper(this);
        presenter = new AddAnimalPresenter(this, new AddAnimalModel());

        setupViews();
        presenter.loadTipoAnimales();
    }

    private void setupViews() {
        etNumero = findViewById(R.id.etNumero);
        etSexo = findViewById(R.id.etSexo);
        etPeso = findViewById(R.id.etPeso);
        spinnerTipoAnimal = findViewById(R.id.spinnerTipoAnimal);
        btnGuardarAnimal = findViewById(R.id.btnGuardarAnimal);
        btnVerAnimales = findViewById(R.id.btnVerAnimales);
        btnVolver = findViewById(R.id.btnVolver);


        btnVolver.setOnClickListener(v -> {
            onBackPressed();

        });



        btnVerAnimales.setOnClickListener(v -> {
            Intent intent = new Intent(AddAnimalView.this, AnimalListView.class);
            startActivity(intent);
        });


        btnGuardarAnimal.setOnClickListener(v -> {
            long crianzaId = preferencesHelper.getCrianzaId();
            if (crianzaId != -1) {
                LocalDate fechaLlegada = LocalDate.now();
                int numero = Integer.parseInt(etNumero.getText().toString());
                String sexo = etSexo.getText().toString();
                BigDecimal peso = new BigDecimal(etPeso.getText().toString());
                TipoAnimal tipoAnimal = (TipoAnimal) spinnerTipoAnimal.getSelectedItem();
                Crianza crianza = new Crianza();
                crianza.setId(crianzaId);

                Animal animal = new Animal(fechaLlegada, numero, sexo, peso, tipoAnimal, crianza);
                presenter.addAnimal(animal);
            } else {
                Toast.makeText(AddAnimalView.this, "No hay crianza activa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showTipoAnimales(List<TipoAnimal> tipoAnimales) {
        ArrayAdapter<TipoAnimal> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoAnimales);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoAnimal.setAdapter(adapter);
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
    public void onAnimalAdded(Animal animal) {
        Toast.makeText(this, "Animal añadido correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
