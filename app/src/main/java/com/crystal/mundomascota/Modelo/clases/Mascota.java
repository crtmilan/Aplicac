package com.crystal.mundomascota.Modelo.clases;

import java.io.Serializable;

public class Mascota implements Serializable {
    private int foto;
    private String nombre;
    private int cantHuesoLike;
    private boolean isFavorita;

    public Mascota(int foto, String nombre, int cantHuesoLike, boolean isFavorita) {
        this.foto = foto;
        this.nombre = nombre;
        this.cantHuesoLike = cantHuesoLike;
        this.isFavorita = isFavorita;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantHuesoLike() {
        return cantHuesoLike;
    }

    public void setCantHuesoLike(int cantHuesoLike) {
        this.cantHuesoLike = cantHuesoLike;
    }

    public boolean isFavorita() {
        return isFavorita;
    }

    public void setFavorita(boolean favorita) {
        isFavorita = favorita;
    }
}
