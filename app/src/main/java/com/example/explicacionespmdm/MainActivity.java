package com.example.explicacionespmdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

/**
 * MEDIA PLAYER
 * @author José Carlos Alfaro
 * @version 0.1
 *
 *  Ejercicio que implementa un reproductor de VIDEO básico. El proyecto está incompleto, ya que
 *  debe añadirse más elementos que controlen el flujo de de los estados.
 *
 *  Consideraciones previas:
 *
 *  1. Consultar la documentación oficial.
 *  2. IMPORTANTE: Comprender y consultar el diagrama de estado del objeto MediaPlayer:
 *  https://developer.android.com/reference/android/media/MediaPlayer#StateDiagram
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SurfaceView surfaceView;
    private Button btnPlay;
    private Button btnStop;

    //Todo 1. Creamos el objeto MediaPlayer. Dependiendo de su estado se podrá llamara a diferentes
    // métodos
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.sfView);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnStop = (Button) findViewById(R.id.btn_stop);



        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    public void playVideo(){



        //Todo 2. Inicializamos el objeto. Aquí hay que tener en cuenta que se puede
        // inicializar de dos formas:
        // Forma 1. Como un objeto normal a través de 'new'. De esta forma el objeto pasará
        //          al estado 'idle' o inactivo.
        // Forma 2. A través del método estático 'create'. De esta forma el objeto pasará
        // directamente al estado 'prepare' o preparado.
        // En este ejercicio se ha usado la forma 1 (Se deja comentada la forma 2)
        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
            //mediaPlayer = MediaPlayer.create(this, R.raw.videoplayback);
        }

        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback));
            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //Todo 4. Comenzamos la reproducción
                mediaPlayer.start();
            }
        });

        //Todo 3. Listener que se encargará de llamarse cuando el video llegue al final.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopVideo();
            }
        });

        //Todo 5. Se controla la visibilidad de los botones para controlar que el usuario
        // no active funciones que el estado del objeto no lo permita
        btnPlay.setEnabled(false);
        btnStop.setEnabled(true);

    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btn_play:

                playVideo();

                break;

            case R.id.btn_stop:
                if(mediaPlayer != null){
                    //Todo 6. Paramos la música.
                    stopVideo();
                    btnStop.setEnabled(false);
                    btnPlay.setEnabled(true);

                }

                break;
        }
    }

    private void stopVideo() {
        mediaPlayer.stop();
        // Todo 7. Se libera la memoria y se elimina la instancia.
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        //Todo 8. Se controla que si el usuario cambia de actividad o para la aplicación
        // el video pare
        stopVideo();
    }

}