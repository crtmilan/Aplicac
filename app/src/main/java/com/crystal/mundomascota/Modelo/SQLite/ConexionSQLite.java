package com.crystal.mundomascota.Modelo.SQLite;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.crystal.mundomascota.Modelo.common.Constantes;
import com.crystal.mundomascota.Modelo.SQLite.dao.MascotasDao;
import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;

import java.util.List;

public class ConexionSQLite {
    @SuppressLint("StaticFieldLeak")
    private static ConexionSQLite conexionSQLite;

    private MascotasDao mascotasDao;

    public ConexionSQLite(Context context){
        MundoMascotasBD mundoMascotasBD = Room.databaseBuilder(context.getApplicationContext(),
                MundoMascotasBD.class, Constantes.BASE_DATOS_MUNDOMASCOTA)
                .allowMainThreadQueries().build();

        mascotasDao = mundoMascotasBD.mascotasDao();
    }

    public static ConexionSQLite get(Context contexto){
        if(conexionSQLite == null){
            conexionSQLite = new ConexionSQLite(contexto);
        }
        return conexionSQLite;
    }

    public void cerrarBD(){
        conexionSQLite = null;
    }

    //region TABLA MASCOTAS

    public void insertarMascota(MascotasEntity mascotasEntity){
        mascotasDao.insert(mascotasEntity);
    }

    public void borrarMascota(MascotasEntity mascotasEntity){
        mascotasDao.delete(mascotasEntity);
    }

    public void actualizarMascota(MascotasEntity mascotasEntity){
        mascotasDao.update(mascotasEntity);
    }

    public List<MascotasEntity> consultarMascotas(){
        return mascotasDao.getAll();
    }

    public int consultarCantidadFavoritos() {
        return mascotasDao.cantidadFavoritos();
    }

    public void setMascotaFavorita(int id, int favorito){
        mascotasDao.setFavorito(id, favorito);
    }

    public List<MascotasEntity> consultarMascotasFavoritas(){
        return mascotasDao.getAllFavoritos();
    }

    public void setLikeMascota(int id, int cantidadLike){
        mascotasDao.setLikeMascota(id, cantidadLike);
    }

    //endregion

}
