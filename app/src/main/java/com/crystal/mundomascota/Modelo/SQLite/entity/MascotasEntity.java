package com.crystal.mundomascota.Modelo.SQLite.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.crystal.mundomascota.Modelo.common.Constantes;

import java.io.Serializable;

@Entity(tableName = Constantes.TABLA_MASCOTAS)
public class MascotasEntity implements Serializable {

    @PrimaryKey()
    @ColumnInfo(name = Constantes.TABLA_MASCOTAS_ID)
    private int id;

    @ColumnInfo(name = Constantes.TABLA_MASCOTAS_NOMBRE)
    private String nombre;

    @ColumnInfo(name = Constantes.TABLA_MASCOTAS_FOTO)
    private int foto;

    @ColumnInfo(name = Constantes.TABLA_MASCOTAS_CANT_LIKE)
    private int cantidadLike;

    @ColumnInfo(name = Constantes.TABLA_MASCOTAS_IS_FAVORITO)
    private boolean favorito;

    public MascotasEntity(int id, String nombre, int foto, int cantidadLike, boolean favorito) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.cantidadLike = cantidadLike;
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getCantidadLike() {
        return cantidadLike;
    }

    public void setCantidadLike(int cantidadLike) {
        this.cantidadLike = cantidadLike;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
