package com.example.explicacionespmdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Actividad: Notificaciones_Toast_Alerts_Logs
 * @author JC
 * @version 0.1
 *
 * Explica el uso de :
 *
 * 1- Toast como comunicación sin interacción con el usuario
 * 2- Alerts como medio de comunicación con el usuario
 * 3- Logs como medio de depuración.
 *
 */
public class MainActivity extends AppCompatActivity {



    Button btn_toast;
    Button btn_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_toast = (Button)   findViewById(R.id.btn_toast);
        btn_alert = (Button)   findViewById(R.id.btn_alert);


        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("Esto es un toast igual que le primero");
            }
        });

        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    /**
     * Método que encapsula la acción Toast, evita tener que repetir código en los parámetros que
     * nunca cambian, como puede ser la duración, el contexto o que se olvide llamar al método show()
     * @param msg Mensaje a mostrar en el toast.
     */
    public void myLongToast(String msg){
        //Todo 1 Un toast no es más que un mensaje de duración determinada SIN necesidad de interacción
        //Todo -> por parte del usuario. Hay que tener en cuenta que puede que el usuario no lo lea
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public AlertDialog createAlertBuilder(String titulo, String mensaje){
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(mensaje)
                .setTitle(titulo);

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        return builder.create();
    }

}