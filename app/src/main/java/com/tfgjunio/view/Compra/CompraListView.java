package com.tfgjunio.view.Compra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tfgjunio.R;
import com.tfgjunio.adapter.CompraAdapter;
import com.tfgjunio.contract.Compra.CompraListContract;
import com.tfgjunio.domain.Compra;
import com.tfgjunio.presenter.Compra.CompraListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CompraListView extends AppCompatActivity implements CompraListContract.View {

    private List<Compra> compraList;
    private CompraAdapter adapter;
    private CompraListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_list_view);

        presenter = new CompraListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        compraList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.compra_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CompraAdapter(this, compraList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Compra", "Llamada desde Compra View");
        presenter.loadAllCompras();

    }

    public void showCompras(List<Compra> compras) {
        compraList.clear();
        compraList.addAll(compras);
        adapter.notifyDataSetChanged();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

