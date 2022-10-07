package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    Button btnSec;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnSec      = (Button) findViewById(R.id.btn_fin_scnd);
        editText    = (EditText) findViewById(R.id.edTxt_scnd);


        btnSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
                if(msg != null && !msg.equals("")){
                    //Todo 2. En este caso creamos un intent para que devuelva el mensaje.
                    //Todo -> Este se entregará cuando la instancia de la actividad que lo devuelve finalice.


                    //Todo 2.1 Se añade el mensaje al intent.


                    //Todo 2.2 Se llama al metodo que añade el intent al resultado.
                    //Todo -> En este caso admite un parámetro inicial "ResultCode" que podrá ser
                    //Todo -> dos valores estáticos dentro de la clase Activity:
                    // Todo -> RESULT_OK en caso de que el resultado devuelto sea correcto
                    //Todo ->  RESULT_CANCELED  en caso de que el resultado devuelto sea incorrecto.


                    //Todo 2.3 Finalizamos la actividad para devolver el resultado.

                }


            }
        });


    }
}