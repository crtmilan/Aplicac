package com.crystal.mundomascota.Presentador;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.crystal.mundomascota.Interface.IMainActivity;
import com.crystal.mundomascota.Modelo.ModeloMainActivity;
import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;
import com.crystal.mundomascota.Modelo.clases.Mascota;
import com.crystal.mundomascota.Vista.fragment.InicioFragment;
import com.crystal.mundomascota.Vista.fragment.PerfilFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PresentadorMainActivity implements IMainActivity.Presentador {

    private Context context;
    private IMainActivity.Vista vista;
    private IMainActivity.Modelo modelo;

    public PresentadorMainActivity(Context context, IMainActivity.Vista vista) {
        this.context = context;
        this.vista = vista;
        modelo = new ModeloMainActivity(context, this);
    }

    @Override
    public ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new InicioFragment(modelo.consultarMascotas(), this));
        fragments.add(new PerfilFragment());

        return fragments;
    }

    @Override
    public int cantidadFavoritos() {
        return modelo.consultarCantidadFavoritos();
    }

    @Override
    public void actualizarVista() {
        vista.actualizarVista();
    }

    @Override
    public List<MascotasEntity> mascotasFavoritas() {
        return modelo.consultarMascotasFavoritas();
    }


    @Override
    public void actualizarFavoritos(int id, int favorito) {
        modelo.actualizarFavoritosBD(id, favorito);
    }

    @Override
    public void actualizarLikeMascota(int id, int cantidadLike) {
        modelo.actualizarLikeMascotaDB(id, cantidadLike);
    }
}
