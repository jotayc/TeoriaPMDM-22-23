package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * PROYECTO BASE
 * @author JC
 * @version 0.1
 *
 *  La idea del proyecto es concentrar en uno solo todas las tareas explicativas
 *  del módulo de 2º de DAM, Programación Multimedia y dispositivos móviles,
 *  haciendo uso de ramas a través de git.
 *
 *  Cada rama será un proyecto de un apartado explicativo independiente.
 *
 *  Esta rama SE DEBE DEJAR SIN IMPLEMENTAR para tenerla como base para comenzar ramas de futuras
 *  explicaciones.
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}