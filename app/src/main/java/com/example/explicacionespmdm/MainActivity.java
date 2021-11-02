package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Actividad Biblioteca externa. En esta actividad se añadirá una biblioteca de terceros.
 * @author JC
 * @version 0.1
 *
 * CONSIDERACIONES PREVIAS:
 *  1. Las biblioteca externa usada se ha buscado a través de la web https://android-arsenal.com/
 *  2. La biblioteca usada es 'Toasty' https://android-arsenal.com/details/1/7781
 *  3. Se va a modificar los archivos .gradle, por lo que se deberá entender el funcionamiento de la
 *  aplicación Gradle
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}