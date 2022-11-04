package com.crystal.mundomascota.Modelo.SQLite.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;

import java.util.List;

@Dao
public interface MascotasDao {
    @Insert
    void insert(MascotasEntity mascotasEntity);

    @Delete
    void delete(MascotasEntity mascotasEntity);

    @Update
    void update(MascotasEntity mascotasEntity);

    @Query("SELECT * FROM Mascotas")
    List<MascotasEntity> getAll();

    @Query("SELECT COUNT(IsFavorito) FROM mascotas WHERE IsFavorito = 1")
    int cantidadFavoritos();

    @Query("UPDATE Mascotas SET IsFavorito=:favorito WHERE Id=:id")
    void setFavorito(int id, int favorito);

    @Query("SELECT * FROM mascotas WHERE IsFavorito = 1")
    List<MascotasEntity> getAllFavoritos();

    @Query("UPDATE Mascotas SET CantidadLike=:cantidadLike WHERE Id=:id")
    void setLikeMascota(int id, int cantidadLike);
}
