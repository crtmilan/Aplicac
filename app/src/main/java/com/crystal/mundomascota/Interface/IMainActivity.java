package com.crystal.mundomascota.Interface;

import androidx.fragment.app.Fragment;

import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;
import com.crystal.mundomascota.Modelo.clases.Mascota;

import java.util.ArrayList;
import java.util.List;

public interface IMainActivity {
    interface Vista{
        void setUpViewPager();
        void inicializar();
        void eventos();
        void actualizarVista();
    }

    interface Presentador{
        ArrayList<Fragment> agregarFragment();
        int cantidadFavoritos();
        void actualizarVista();
        List<MascotasEntity> mascotasFavoritas();
        void actualizarFavoritos(int id, int favorito);
        void actualizarLikeMascota(int id, int cantidadLike);
    }

    interface Modelo{
        List<MascotasEntity> consultarMascotas();
        List<MascotasEntity> consultarMascotasFavoritas();
        int consultarCantidadFavoritos();
        void actualizarFavoritosBD(int id, int favorito);
        void actualizarLikeMascotaDB(int id, int cantidadLike);
    }
}
