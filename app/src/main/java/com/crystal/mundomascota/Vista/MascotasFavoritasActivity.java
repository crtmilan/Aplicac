package com.crystal.mundomascota.Vista;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;
import com.crystal.mundomascota.Modelo.clases.Mascota;
import com.crystal.mundomascota.Modelo.common.Utilidades;
import com.crystal.mundomascota.Presentador.PresentadorMainActivity;
import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Vista.adapter.MascotasRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MascotasFavoritasActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivPatitaActionBarHard;
    RecyclerView rvMascotasFavoritas;
    MascotasRecyclerViewAdapter adaptador;
    List<MascotasEntity> listaMascotasFavoritas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_hardcodeadas);
        Utilidades.ocultarBarraEstado(getWindow());

        inicializar();
        eventos();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if(!listaMascotasFavoritas.isEmpty()){
            adaptador = new MascotasRecyclerViewAdapter(listaMascotasFavoritas, null);
            rvMascotasFavoritas.setAdapter(adaptador);
        }
    }

    private void inicializar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.ActionBarHard);
        setSupportActionBar(myToolbar);

        ivPatitaActionBarHard = findViewById(R.id.ivPatitaActionBarHard);
        rvMascotasFavoritas = findViewById(R.id.rvMascotasFavoritas);
        rvMascotasFavoritas.setLayoutManager(new LinearLayoutManager(this));

        listaMascotasFavoritas = (List<MascotasEntity>) getIntent().getSerializableExtra(getResources().getString(R.string.mascotasFavoritas));
    }

    private void eventos() {
        ivPatitaActionBarHard.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menuperrito2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mItemOtroMenuPerrito) {
            Toast.makeText(this, "En construcción menú perrito 2", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ivPatitaActionBarHard){
            Toast.makeText(this, "Patita favoritos", Toast.LENGTH_SHORT).show();
        }
    }
}