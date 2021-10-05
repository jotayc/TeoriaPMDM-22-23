package com.example.explicacionespmdm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Actividad Modificación de comunicación entre actividades B hacia A
 * @author JC
 * @version 0.1
 *
 * En esta actividad se explica:
 * 1. Comunicación con datos entre actividades B hacia A
 *
 */
public class MainActivity extends AppCompatActivity {

    TextView txtVwMsg;
    Button btnMain;

    //Todo 1.2 el número es indiferente pero no debe repetirse para otros request code
    public static int RQ_SECOND_ACT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwMsg = (TextView) findViewById(R.id.txtVw_main);
        btnMain = (Button) findViewById(R.id.btn_main);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);

                //Todo 1. Llamamos a la variante del método que lanza la actividad que indica que esta
                //Todo -> esta esperará un resultado.

                // Todo 1.1 El segundo parametro "Request Code" se encargará de identificar de qué actividad
                // Todo -> está recibiendo el mensaje.

                startActivityForResult(i,RQ_SECOND_ACT);
            }
        });


    }

    //Todo 3.1 Sobrecargamos el método onActivityResult
    /**
     * Método que recibe información de una actividad lanzada posteriormente
     * @param requestCode Codigo que identifica qué actividad envía el mensaje.
     * @param resultCode Código que identifica si el mensaje que ha recibido es correcto o no.
     * @param data Contiene el mensaje.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Todo 3.2 Usamos RQ_SECOND_ACT para saber si el mensaje proviene de la segunda actividad
        if(requestCode == RQ_SECOND_ACT){
            //Todo 3.3 Usamos RESULT_OK para saber si el mensaje que llega es el correcto.
            if(resultCode==RESULT_OK){
                //Todo 3.4 Extraemos la información del intent y lo usamos
                String msg = data.getStringExtra("result");
                txtVwMsg.setText(msg);
            }

        }

    }
}