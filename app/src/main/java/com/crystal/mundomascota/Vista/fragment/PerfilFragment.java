package com.crystal.mundomascota.Vista.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Vista.adapter.PerfilMascotaRecyclerViewAdapter;
import com.crystal.mundomascota.Modelo.clases.Mascota;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    RecyclerView rvPerfilMascota;
    TextView tvNombrePerfil;
    PerfilMascotaRecyclerViewAdapter adaptador;
    List<Mascota> listaPerfilMascota;
    View view;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        inicializar();
    }

    private void inicializar() {
        rvPerfilMascota = view.findViewById(R.id.rvPerfilMascota);
        rvPerfilMascota.setLayoutManager(new GridLayoutManager(getContext(), 3));
        tvNombrePerfil = view.findViewById(R.id.tvNombrePerfil);
        tvNombrePerfil.setText(getResources().getString(R.string.nombre_principal_mascota));

        crearListaPerfil();

        adaptador = new PerfilMascotaRecyclerViewAdapter(listaPerfilMascota);
        rvPerfilMascota.setAdapter(adaptador);
    }

    private void crearListaPerfil() {
        listaPerfilMascota = new ArrayList<>();
        for(int i=1; i<=20; i++){
            listaPerfilMascota.add(new Mascota(R.drawable.feliz_foto_perfil, "Malu", i*4, false));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}