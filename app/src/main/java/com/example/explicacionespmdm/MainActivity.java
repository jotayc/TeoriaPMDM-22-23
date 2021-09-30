package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

/**
 * Actividad 2. Interfaz_Diseño_Spinner
 * @author JC
 * @version 0.1
 *
 * En esta actividad se mostrará:
 *
 * 1- Elemento de diseño de contenido múltiple: Spinner
 * 2- El uso de adaptadores y su relación teórica (Lista - Adaptador - Vista)
 * 3- El uso de control de selección (Eventos)
 * 4- Diseño XML de los elementos desplegables.
 *
 */
public class MainActivity extends AppCompatActivity {

    //Declaración de los elementos de la interfaz
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de los elementos de la interfaz.
        spinner = (Spinner) findViewById(R.id.spinner);

    }
}