package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

    //Todo 1. Declaración de los elementos de la interfaz
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo 2. Inicialización de los elementos de la interfaz.
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this,R.array.provincias, android.R.layout.simple_spinner_item);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String provincia = (String) adapterView.getItemAtPosition(i);
                myToast(provincia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myToast("Nada seleccinado");
            }
        });


    }

    public void myToast(String msg){
        //Todo 3. Creamos una notificación simple
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}