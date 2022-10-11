package com.example.explicacionespmdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * Actividad: Notificaciones_Toast_Alerts_Logs
 * @author JC
 * @version 0.1
 *
 * Ejercicio hazlo tu mismo :
 *
 * 1- Toast como comunicación sin interacción con el usuario
 * 2- Alerts como medio de comunicación con el usuario
 * 3- Logs como medio de depuración.
 *
 */
public class MainActivity extends AppCompatActivity {


    Button btn_toast;
    Button btn_alert;
    Button btn_snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_toast = (Button)   findViewById(R.id.btn_toast);
        btn_alert = (Button)   findViewById(R.id.btn_alert);
        btn_snack = (Button)   findViewById(R.id.btn_snack);


        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("Esto es un toast igual que le primero");
            }
        });

        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 2.6 Solo debemos crear un objeto AlertDialog y llamar al método abajo declarado
                // que lo crea.
                //Todo -> Posteriormente lo mostramos.

            }
        });

        btn_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Todo 3. Creamos el snackbar y lo mostramos

            }
        });

    }


    /**
     * Método que encapsula la acción Toast, evita tener que repetir código en los parámetros que
     * nunca cambian, como puede ser la duración,
     * el contexto o que se olvide llamar al método show()
     * @param msg Mensaje a mostrar en el toast.
     */
    public void myLongToast(String msg){
        //Todo 1 Un toast no es más que un mensaje de duración determinada SIN necesidad de interacción
        //Todo -> por parte del usuario. Hay que tener en cuenta que puede que el usuario no lo lea
        // Configura el Toast con una duración Larga.
    }

    /**
     * Esto es un método que encapsula la creación de cuadros de dialogos simples
     * @param titulo Contiene el título del cuadro de dialogo
     * @param mensaje Contiene el mensaje dentro del cuadro de dialogo
     * @return Devuelve el objeto AlertDialog
     */
    public AlertDialog createAlertDialog(String titulo, String mensaje){
        // Todo 2.1 Creamos un 'builder' o constructor que nos ayude a configurar el cuadro de
        //  dialogo pasandole el contexto


        // Todo 2.2 Este objeto builder nos permitirá añadir todas las configuraciones que se necesiten
        // Todo -> antes de crear el alert. En este ejemplo se añade el mensaje y el titulo del alert

        //Todo 2.3 Configura el botón positivo, a través del builder, para asociar una acción
        // Muestra un toast


        //Todo 2.4 Configura el botón positivo, a través del builder, para asociar una acción


        // Todo 2.5 Una vez hemos añadido todas las configuraciones creamos el alertDialog. En este
        // Todo -> caso, devolvemos el objeto creado.

    }

}