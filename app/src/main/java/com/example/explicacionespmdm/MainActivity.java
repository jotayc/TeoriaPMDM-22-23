package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.explicacionespmdm.db.Nota;
import com.orm.SugarContext;
import com.prathameshmore.toastylibrary.Toasty;

/**
 * Actividad Biblioteca externa. En esta actividad se describirá el proceso de gestión de bibliotecas
 * externas.
 * @author JC
 * @version 0.1
 *
 * CONSIDERACIONES PREVIAS:
 * BIBLIOTECA SUGARORM:
 *  1. La página web del proyecto es https://github.com/chennaione/sugar
 *  2. Documentación: http://satyan.github.io/sugar/index.html
 *
 * BIBLIOTECA TOASTY:
 *  1. Las biblioteca externa usada se ha buscado a través de la web https://android-arsenal.com/
 *  2. La biblioteca usada es 'Toasty' https://android-arsenal.com/details/1/7781
 *
 * IMPORTANTE (AMBOS):
 *  . Se va a modificar los archivos settings.gradle y build.gradle, por lo que se deberá entender el funcionamiento de la
 *  aplicación Gradle
 *
 */
public class MainActivity extends AppCompatActivity {

    private EditText mTextoNota;
    private Button mGuardar;
    private Button mBorrar;

    private Nota mNota;

    final Toasty toasty =  new Toasty(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo 3.1 Configuración: Inicializa la clase de la biblioteca que gestionará la base de datos
        SugarContext.init(this);

        mTextoNota  = (EditText) findViewById(R.id.texto_nota);
        mGuardar    = (Button)   findViewById(R.id.boton_guardar);
        mBorrar    = (Button)   findViewById(R.id.boton_borrar);

        //Todo 4.1 Tal como viene en la documentación. Se usa el método findById para hacer consultas a la BD
        mNota = Nota.findById(Nota.class,1);


        if(mNota != null){
            mTextoNota.setText(mNota.getMensaje());
            Log.d("DB", "1" + mNota.toString());
        }

        mGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guadar();
            }
        });


        mBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });
    }

    private void borrar() {
        if(mNota != null) {

            mNota.delete();
            mNota = null;
            mTextoNota.setText("");

            toasty.lightToasty(this,"Nota borrada",Toasty.LENGTH_SHORT,Toasty.CENTER);
        } else {

            toasty.dangerToasty(this,"La nota no exite",Toasty.LENGTH_SHORT,Toasty.CENTER);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Todo 3.2 Configuración: Es necesario "cerrar" la base de datos cuando la aplicación termine.
        SugarContext.terminate();
    }

    private void guadar() {

        String textoNota = mTextoNota.getText().toString();

        if(!textoNota.equals("")) {
            if(mNota == null) {
                mNota = new Nota();
                mNota.setMensaje(textoNota);

                //Todo 4.2 Se usa el método save() para guardar el objeto en la BD
                long save = mNota.save();
                Log.d("DB", "Long: " + save);

                toasty.primaryToasty(this,"Nota creada id: " + save,Toasty.LENGTH_SHORT,Toasty.CENTER);


            } else {
                mNota.setMensaje(textoNota);

                //Todo 4.3 Se usa el método update() para actualizar el objeto en la BD
                long update = mNota.update();

                toasty.successToasty(this,"Nota actualizada id: " + update,Toasty.LENGTH_SHORT,Toasty.TOP);
            }
        } else {
            toasty.infoToasty(this,"Debe crear una nota primero",Toasty.LENGTH_SHORT,Toasty.BOTTOM);
        }
    }

    // Todo ---- PARTE 2: TOASTY LIBRARY ----
    public void myToasty(String msg){
        toasty.infoToasty(this,msg,Toasty.LENGTH_SHORT,Toasty.TOP);
    }
}