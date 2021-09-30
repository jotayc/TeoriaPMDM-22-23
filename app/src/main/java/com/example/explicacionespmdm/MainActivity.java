package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.view.View;
import android.widget.Button;
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

    TextView txtVwMain;
    Button   btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwMain = (TextView) findViewById(R.id.txtVw_main);
        btn_main  = (Button) findViewById(R.id.btn_main);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}