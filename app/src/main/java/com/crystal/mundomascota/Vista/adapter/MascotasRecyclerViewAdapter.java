package com.crystal.mundomascota.Vista.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.crystal.mundomascota.Interface.IMainActivity;
import com.crystal.mundomascota.Modelo.SQLite.entity.MascotasEntity;
import com.crystal.mundomascota.Modelo.common.Constantes;
import com.crystal.mundomascota.Modelo.common.Utilidades;
import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Modelo.clases.Mascota;
import com.crystal.mundomascota.Vista.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MascotasRecyclerViewAdapter extends RecyclerView.Adapter<MascotasRecyclerViewAdapter.MascotasViewHolder>{

    List<MascotasEntity> listaMascotas;
    MascotasEntity mascota;
    private IMainActivity.Presentador presentador;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MascotasRecyclerViewAdapter(List<MascotasEntity> listaMascotas, IMainActivity.Presentador presentador) {
        this.listaMascotas = listaMascotas;
        this.presentador = presentador;

        /*Esto funciona solo de la API 24 en adelante, es para ordenar las mascotas dependiento de los likes*/
        listaMascotas.sort(Comparator.comparing(MascotasEntity::getCantidadLike).reversed());
    }

    @NonNull
    @NotNull
    @Override
    public MascotasViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_mascotas, parent, false);
        return new MascotasRecyclerViewAdapter.MascotasViewHolder(view, presentador);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull @NotNull MascotasRecyclerViewAdapter.MascotasViewHolder holder, int position) {
        mascota = listaMascotas.get(position);
        holder.tvIdMascota.setText(Integer.toString(mascota.getId()));
        holder.ivFotoMascota.setImageResource(mascota.getFoto());
        holder.tvNombreMascota.setText(mascota.getNombre());
        holder.tvCantHuesoLike.setText(Integer.toString(mascota.getCantidadLike()));

        if(presentador == null){
            holder.ivNoFavorito.setVisibility(View.GONE);
            holder.ivFavorito.setVisibility(View.GONE);
            holder.ivHuesoLikeBlanco.setVisibility(View.GONE);
        }else{
            if(mascota.isFavorito()){
                holder.ivFavorito.setVisibility(View.VISIBLE);
                holder.ivNoFavorito.setVisibility(View.GONE);
            }else{
                holder.ivFavorito.setVisibility(View.GONE);
                holder.ivNoFavorito.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public static class MascotasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View view;
        public final ImageView ivFotoMascota, ivHuesoLikeBlanco, ivHuesoLike, ivFavorito, ivNoFavorito;
        public final TextView tvNombreMascota, tvCantHuesoLike, tvIdMascota;
        public Context contexto;
        public IMainActivity.Presentador presentador;

        public MascotasViewHolder(@NonNull @NotNull View v, IMainActivity.Presentador presentador) {
            super(v);
            view = v;
            contexto = view.getContext();
            this.presentador = presentador;
            ivFotoMascota = view.findViewById(R.id.ivFotoMascota);
            ivHuesoLikeBlanco = view.findViewById(R.id.ivHuesoLikeBlanco);
            tvNombreMascota = view.findViewById(R.id.tvNombreMascota);
            tvCantHuesoLike = view.findViewById(R.id.tvCantHuesoLike);
            ivHuesoLike = view.findViewById(R.id.ivHuesoLike);
            ivFavorito = view.findViewById(R.id.ivFavorito);
            ivNoFavorito = view.findViewById(R.id.ivNoFavorito);
            tvIdMascota = view.findViewById(R.id.tvIdMascota);

            ivHuesoLikeBlanco.setOnClickListener(this);
            ivFavorito.setOnClickListener(this);
            ivNoFavorito.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        @Override
        public void onClick(View v) {
            int id = Integer.parseInt(tvIdMascota.getText().toString());
            String nombre = tvNombreMascota.getText().toString();
            if(v.getId() == R.id.ivHuesoLikeBlanco){
                int cantHuesoLike = Integer.parseInt(tvCantHuesoLike.getText().toString())+1;
                animacionParpadeo();
                tvCantHuesoLike.setText(Integer.toString(cantHuesoLike));
                presentador.actualizarLikeMascota(id, cantHuesoLike);
            }else if(v.getId() == R.id.ivFavorito){
                ivFavorito.setVisibility(View.GONE);
                ivNoFavorito.setVisibility(View.VISIBLE);
                Utilidades.mensajeToast(contexto, nombre+ " se quito de tus favoritos", Toast.LENGTH_LONG);
                presentador.actualizarFavoritos(id, 0);
                presentador.actualizarVista();
            }else if(v.getId() == R.id.ivNoFavorito){
                ivFavorito.setVisibility(View.VISIBLE);
                ivNoFavorito.setVisibility(View.GONE);
                Utilidades.mensajeToast(contexto, nombre+ " se agrego a tus favoritos", Toast.LENGTH_LONG);
                presentador.actualizarFavoritos(id, 1);
                presentador.actualizarVista();
            }
        }

        @SuppressLint("ResourceAsColor")
        private void animacionParpadeo() {
            Animation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(2000);
            animation.setBackgroundColor(R.color.colorAccent);
            tvCantHuesoLike.startAnimation(animation);
        }
    }
}
