package com.crystal.mundomascota.Modelo.common;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Utilidades {
    public static final String CORREO_ENVIA = "Trabajoscoursera2022@gmail.com";
    public static final String CONTRASENA_ENVIA = "Trabajos2022";

    public static void ocultarBarraEstado(Window window){
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void mensajeToast(Context context, String mensaje, int duracion){
        Toast.makeText(context, mensaje, duracion).show();
    }
}
