package com.crystal.mundomascota.Modelo;

import android.content.Context;

import com.crystal.mundomascota.Interface.IMainActivity;
import com.crystal.mundomascota.Modelo.SQLite.ConexionSQLite;
import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;
import com.crystal.mundomascota.Modelo.clases.Mascota;
import com.crystal.mundomascota.R;

import java.io.Serializable;
import java.util.List;

public class ModeloMainActivity implements IMainActivity.Modelo, Serializable {

    private Context context;
    private IMainActivity.Presentador presentador;
    private ConexionSQLite conexionSQLite;
    private List<MascotasEntity> mascotas;

    public ModeloMainActivity(Context context, IMainActivity.Presentador presentador) {
        this.context = context;
        this.presentador = presentador;
        conexionSQLite = ConexionSQLite.get(context);
    }

    @Override
    public List<MascotasEntity> consultarMascotas() {
        mascotas = conexionSQLite.consultarMascotas();
        if(mascotas.isEmpty()){
            conexionSQLite.insertarMascota(new MascotasEntity(1,"Odie", R.drawable.odie, 231, false));
            conexionSQLite.insertarMascota(new MascotasEntity(2,"Snoopy", R.drawable.snoopy, 199, false));
            conexionSQLite.insertarMascota(new MascotasEntity(3,"Slinky", R.drawable.slinky, 180, false));
            conexionSQLite.insertarMascota(new MascotasEntity(4,"Toto", R.drawable.toto, 123, false));
            conexionSQLite.insertarMascota(new MascotasEntity(5,"Balto", R.drawable.balto, 101, false));
            conexionSQLite.insertarMascota(new MascotasEntity(6,"Marley", R.drawable.marley, 96, false));
            conexionSQLite.insertarMascota(new MascotasEntity(7,"Bolt", R.drawable.bolt, 77, false));
            conexionSQLite.insertarMascota(new MascotasEntity(8,"Golfo", R.drawable.golfo, 23, false));

            mascotas = conexionSQLite.consultarMascotas();
        }
        return mascotas;
    }

    @Override
    public List<MascotasEntity> consultarMascotasFavoritas() {
        return conexionSQLite.consultarMascotasFavoritas();
    }

    @Override
    public int consultarCantidadFavoritos() {
        return conexionSQLite.consultarCantidadFavoritos();
    }

    @Override
    public void actualizarFavoritosBD(int id, int favorito) {
        conexionSQLite.setMascotaFavorita(id, favorito);
    }

    @Override
    public void actualizarLikeMascotaDB(int id, int cantidadLike) {
        conexionSQLite.setLikeMascota(id, cantidadLike);
    }
}
