package com.crystal.mundomascota.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Modelo.common.Utilidades;

import java.util.Objects;

public class InfoDesarrolladorActivity extends AppCompatActivity {

    TextView tvInfoDesarrollador, tvTituloActionBarHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_desarrollador);
        Utilidades.ocultarBarraEstado(getWindow());

        inicializar();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void inicializar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.ActionBarInfoDev);
        setSupportActionBar(myToolbar);

        tvTituloActionBarHard = findViewById(R.id.tvTituloActionBarHard);
        tvTituloActionBarHard.setText(getResources().getString(R.string.menu_acercade));

        tvInfoDesarrollador = findViewById(R.id.tvInfoDesarrollador);
        tvInfoDesarrollador.setText(crearInfo());
    }

    private String crearInfo() {
        return "Tengo más de 1 año y medio de experiencia en el área de desarrollo de software.\n\n" +
                "Poseo habilidades para trabajar en equipo y interactuar fácil con los compañeros de la organización," +
                "con una mente abierta al aprendizaje y muy responsable con mis deberes: soy lógico, organizado," +
                "deliberado: antes de actuar pinso las cosas y sus consecuencias, objetivo, detallado, analítico:" +
                "(reviso, hechos, causas), preciso.\n\n"+
                "DESCRIPCIÓN DE COMPETENCIAS: \n"+
                "-Manejo de base de datos SQL server, SQLite y MySQL.\n"+
                "-Manejo de lenguajes de programación como Java, Kotlin, JavaScript, TypeScript y C#.\n"+
                "-Creación y consumo de API's.\n"+
                "-Dominio de herramientas de Microsoft como PowerApps en el contexto de Office 365, Visual Studio Code y Visual Studio.\n"+
                "-Domino de la herramienta Android Studio.\n"+
                "-Patrón de diseño MVC y MVP.\n"+
                "-Metodología ágiles (Scrum).\n"+
                "-Diseño de aplicativos móviles en Android con back-end en NodeJs o .Net.\n";
    }
}