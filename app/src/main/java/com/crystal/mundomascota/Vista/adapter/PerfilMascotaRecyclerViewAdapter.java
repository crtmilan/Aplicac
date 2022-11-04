package com.crystal.mundomascota.Vista.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Modelo.clases.Mascota;

import java.util.List;

public class PerfilMascotaRecyclerViewAdapter extends RecyclerView.Adapter<PerfilMascotaRecyclerViewAdapter.PerfilMascotaViewHolder>{

    List<Mascota> listaFotosPerfil;

    public PerfilMascotaRecyclerViewAdapter(List<Mascota> listaFotosPerfil) {
        this.listaFotosPerfil = listaFotosPerfil;
    }

    @NonNull
    @NotNull
    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_perfilmascota, parent, false);
        return new PerfilMascotaRecyclerViewAdapter.PerfilMascotaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull PerfilMascotaRecyclerViewAdapter.PerfilMascotaViewHolder holder, int position) {
        holder.ivFotoMascotaPerfil.setImageResource(listaFotosPerfil.get(position).getFoto());
        holder.tvCantHuesoLikePerfil.setText(Integer.toString(listaFotosPerfil.get(position).getCantHuesoLike()));
    }

    @Override
    public int getItemCount() {
        return listaFotosPerfil.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView ivFotoMascotaPerfil;
        public final TextView tvCantHuesoLikePerfil;
        public Context contexto;

        public PerfilMascotaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
            contexto = view.getContext();
            ivFotoMascotaPerfil = view.findViewById(R.id.ivFotoMascotaPerfil);
            tvCantHuesoLikePerfil = view.findViewById(R.id.tvCantHuesoLikePerfil);
        }
    }
}
