package com.crystal.mundomascota.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.mundomascota.Interface.IMainActivity;
import com.crystal.mundomascota.Presentador.PresentadorMainActivity;
import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Vista.adapter.PageAdapter;
import com.crystal.mundomascota.Modelo.clases.Mascota;
import com.crystal.mundomascota.Modelo.common.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainActivity.Vista {

    PresentadorMainActivity presentador;
    Context context;
    ImageView ivPatitaActionBar;
    TextView tvTituloCantHard;
    FloatingActionButton fabSubirFotoMascota;
    PageAdapter pageAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utilidades.ocultarBarraEstado(getWindow());

        inicializar();
        eventos();
        setUpViewPager();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ivPatitaActionBar){
            Toast.makeText(this, "Patita principal", Toast.LENGTH_SHORT).show();
        }else if(v.getId() == R.id.tvTituloCantHard){
            if(presentador.cantidadFavoritos() == 0){
                Utilidades.mensajeToast(context, "No tienes favoritos", Toast.LENGTH_SHORT);
            }else{
                irMascotasFavoritas();
            }
        }else if(v.getId() == R.id.fabSubirFotoMascota){
            //Aca se puede implementar el metodo para abrir la camara.
            Toast.makeText(this, "Abriendo camara...", Toast.LENGTH_SHORT).show();
        }
    }

    private void irMascotasFavoritas() {
        Intent i = new Intent(MainActivity.this, MascotasFavoritasActivity.class);
        i.putExtra(getResources().getString(R.string.mascotasFavoritas), (Serializable) presentador.mascotasFavoritas());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menuperrito, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mItemMenuContacto) {
            irContacto();
        }else if(item.getItemId() == R.id.mItemMenuAcercaDe){
            irInfoDesarrollador();
        }
        return super.onOptionsItemSelected(item);
    }

    private void irInfoDesarrollador() {
        Intent i = new Intent(MainActivity.this, InfoDesarrolladorActivity.class);
        startActivity(i);
    }

    private void irContacto() {
        Intent i = new Intent(MainActivity.this, EnviarComentarioActivity.class);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setUpViewPager() {
        pageAdapter = new PageAdapter(getSupportFragmentManager(), presentador.agregarFragment());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.icon_casaperro);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.icon_caraperro);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(getResources().getString(R.string.tab_inicio));
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(getResources().getString(R.string.tab_perfil));
        tvTituloCantHard.setText(Integer.toString(presentador.cantidadFavoritos()));
    }

    @Override
    public void inicializar() {
        ivPatitaActionBar = findViewById(R.id.ivPatitaActionBar);
        context = getApplicationContext();

        Toolbar myToolbar = findViewById(R.id.ActionBar);
        setSupportActionBar(myToolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tvTituloCantHard = findViewById(R.id.tvTituloCantHard);
        fabSubirFotoMascota = findViewById(R.id.fabSubirFotoMascota);

        presentador = new PresentadorMainActivity(context, this);
    }

    @Override
    public void eventos() {
        ivPatitaActionBar.setOnClickListener(this);
        tvTituloCantHard.setOnClickListener(this);
        fabSubirFotoMascota.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void actualizarVista() {
        tvTituloCantHard.setText(Integer.toString(presentador.cantidadFavoritos()));
    }
}