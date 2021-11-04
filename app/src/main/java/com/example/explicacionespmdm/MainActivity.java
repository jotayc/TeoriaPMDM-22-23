package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Actividad Interfaz: Button
 * @author JC
 * @version 0.1
 *
 *  En esta rama se explicará los conceptos:
 *  1- Declaración e Inicialización de objetos TextView y Button
 *  2- Clases anónimas y uso de interfaces
 *  3- Conexión entre actividad y layout
 *  4- Ciclo de vida de las actividades
 *
 */
public class MainActivity extends AppCompatActivity {


    //Todo 2: Declaración de objetos
    private Button btn;
    private TextView txtVw;

    //Todo 1: OnCreate como primera función para inicializar elementos de la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Todo 3: Metodo para conectar la vista con la actividad.
        setContentView(R.layout.activity_main);


        //Todo 4: Inicialización de variables
        btn = (Button) findViewById(R.id.button);
        txtVw = (TextView) findViewById(R.id.textView);

        //Todo 5. Los botones tienen un listener según el tipo de toque sobre la vista.
        //Todo -> para ello se usa una clase anonima y se implementa su interfaz.
        //Todo -> Desde Java 8 se sustituyen las clases anonimas por lambdas
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                txtVw.setText("Hola mundo");
                return false;
            }
        });

    }

    //Todo 6. Los siguientes métodos forman parte del ciclo de vida de cualquier actividad
    //Todo    Se podría ver como un diagrama de estados.
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}