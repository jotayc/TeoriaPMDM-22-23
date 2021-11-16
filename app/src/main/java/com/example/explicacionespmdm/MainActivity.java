package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

/**
 * Actividad Glide: En esta actividad se mostrará un ejemplo simple de carga de imagen a través de
 * la biblioteca Glide
 * @author José Carlos Alfaro
 * @version 0.1
 *
 * CONSIDERACIONES PREVIAS:
 *
 * 1. Se debe añadir la biblioteca glide en el archivo build.gladle
 * 2. IMPORTANTE: Dar permiso a internet en AndroidManifest.xml
 * 3. Url de la documentación: https://bumptech.github.io/glide/doc/getting-started.html
 *
 */
public class MainActivity extends AppCompatActivity {


    private ImageView   imageView;
    private Button      loadBtn;
    private CircularProgressDrawable progressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView   = (ImageView) findViewById(R.id.imgView);
        loadBtn     =   (Button) findViewById(R.id.btn_load);
        progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setStyle(CircularProgressDrawable.LARGE);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this)
                        .load("https://as1.ftcdn.net/v2/jpg/01/20/68/68/1000_F_120686889_nDaqiMH8I5AmT5B0hpuJ14ZasdrrgRAK.jpg")
                        .placeholder(progressDrawable)
                        .error(R.mipmap.ic_launcher)
                        .into(imageView);
            }
        });


    }
}