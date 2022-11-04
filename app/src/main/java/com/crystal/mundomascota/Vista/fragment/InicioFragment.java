package com.crystal.mundomascota.Vista.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crystal.mundomascota.Interface.IMainActivity;
import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;
import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Vista.adapter.MascotasRecyclerViewAdapter;
import com.crystal.mundomascota.Modelo.clases.Mascota;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InicioFragment extends Fragment {
    RecyclerView rvMascotas;
    MascotasRecyclerViewAdapter adaptador;
    List<MascotasEntity> listaMascotas;
    View view;
    private IMainActivity.Presentador presentador;

    public InicioFragment(List<MascotasEntity> listaMascotas, IMainActivity.Presentador presentador){
        this.listaMascotas = listaMascotas;
        this.presentador = presentador;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        inicializar();
    }

    private void inicializar() {
        rvMascotas = view.findViewById(R.id.rvMascotas);
        rvMascotas.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptador = new MascotasRecyclerViewAdapter(listaMascotas, presentador);
        rvMascotas.setAdapter(adaptador);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }
}