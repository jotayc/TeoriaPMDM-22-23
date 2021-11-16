package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.explicacionespmdm.io.HttpConnectPokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Actividad peticiones REST: En esta actividad se verán las bases de las peticiones REST a través
 * de las aplicaciones Android
 * @author José Carlos Alfaro
 * @version 0.1
 *
 * CONSIDERACIONES PREVIAS:
 *  1. En este ejemplo se usa la vista ListView para simplificar el ejemplo. Se recomienda el uso
 *  de RecyclerView
 *
 *  2. Hay que tener en cuenta los permisos en Android Manifest, en este ejemplo es necesario
 *  el uso de internet.
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> pokeList = new ArrayList<String>();

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pokeList);
        listview.setAdapter(adapter);

        new taskConnections().execute("GET", "/pokemon?offset=100&limit=100");

    }

    //Todo 2. (ver clase HttpConnectPokemon.java ) Al ser una tarea que implica una espera,
    // como es la respuesta del servidor, por ello se tiene que llevar a cabo a través de un hilo
    // secundario.
    private class taskConnections extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            switch (strings[0]){
                case "GET":
                    result = HttpConnectPokemon.getRequest(strings[1]);
                    break;
                case "POST":
                    result = Integer.toString(HttpConnectPokemon.postRequest(strings[1],strings[2]));
                    break;
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if(s != null){
                    Log.d("D","DATOS: "+s);

                    //Todo 3. La respuesta que nos devuelve es un texto en formato JSON. Para ello,
                    // en este caso, haremos uso de las clases que nos proporciona Android. Antes
                    // que nada, se deberá consultar la documentación para conocer el formato de
                    // la respuesta del servidor, y así saber cómo deserializar el mensaje.
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    String name = "";
                    for(int i=0; i<jsonArray.length(); i++){
                        name = jsonArray.getJSONObject(i).getString("name");
                        pokeList.add(name);
                    }
                    //Todo 3.1 Una vez tenemos los datos en nuestra colección debemos avisar al
                    // adaptador que la información ha cambiado.
                    adapter.notifyDataSetChanged();
                    Log.d("D","Array: "+pokeList.toString());


                }else{
                    Toast.makeText(MainActivity.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}