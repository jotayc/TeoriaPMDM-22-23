package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {



    TextView txtVwSec;
    Button btnSec;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtVwSec    = (TextView) findViewById(R.id.txtVw_scnd);
        btnSec      = (Button) findViewById(R.id.btn_fin_scnd);
        editText    = (EditText) findViewById(R.id.edTxt_scnd);


        Intent i = getIntent();

        String msg = i.getStringExtra("INFO");
        txtVwSec.setText(msg);


        btnSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}