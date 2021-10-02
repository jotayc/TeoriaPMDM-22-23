package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Actividad Movimiento entre actividades y comunicación.
 * @author JC
 * @version 0.1
 *
 * En esta actividad se explica:
 * 1. Intents explicitos
 * 2. Lanzar una nueva actividad
 * 3. Comunicación entre actividades.
 *
 */
public class MainActivity extends AppCompatActivity {

    EditText edTxtMain;
    Button btnMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTxtMain = (EditText) findViewById(R.id.edtxt_main);
        btnMain = (Button) findViewById(R.id.btn_main);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);

                String msg = edTxtMain.getText().toString();
                if(msg != null && msg != "")
                    i.putExtra("INFO", msg);

                startActivity(i);
            }
        });


    }
}