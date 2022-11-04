package com.crystal.mundomascota.Modelo.SQLite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.crystal.mundomascota.Modelo.common.Constantes;
import com.crystal.mundomascota.Modelo.SQLite.dao.MascotasDao;
import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;

@Database(entities = {MascotasEntity.class},
        version = Constantes.BASE_DATOS_MUNDOMASCOTA_VERSION, exportSchema = false)

public abstract class MundoMascotasBD extends RoomDatabase {
    public abstract MascotasDao mascotasDao();
}
