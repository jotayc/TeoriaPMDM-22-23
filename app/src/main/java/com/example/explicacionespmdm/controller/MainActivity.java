package com.example.explicacionespmdm.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.explicacionespmdm.R;

/**
 * Actividad Preferencias: En esta actividad se estudiará la persistencia de datos simples como son
 * las configuraciones de usuario a través de la API propia de Android.
 *
 * @author JC
 * @version 0.1
 *
 *  Consideraciones previas:
 *
 *    1 - El diseño de la configuración debe estar en la carpeta res/xml
 *    2 - Consultar la guia oficial https://developer.android.com/guide/topics/ui/settings?hl=es-419
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private Button   mPrefBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);
        mPrefBtn = (Button)  findViewById(R.id.prefBtn);

        mPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

        loadPreferences();

    }


    public void loadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String emailText = sharedPreferences.getString("emailPreference","Valor por defecto");
        txtView.setText(emailText);
    }
}