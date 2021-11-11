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

        new taskConnections().execute("GET", "/pokemon");

    }

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
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    String name = "";
                    for(int i=0; i<jsonArray.length(); i++){
                        name = jsonArray.getJSONObject(i).getString("name");
                        pokeList.add(name);
                    }

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