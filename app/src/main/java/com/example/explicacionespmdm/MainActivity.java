package com.example.explicacionespmdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Actividad: Menus contextuales
 * @author JC
 * @version 0.1
 *
 * Explica el uso de :
 *
 * 1- Menu de acción
 *
 */
public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.view.ActionMode mActionMode;

    private Button btn_floating;
    private Button btn_action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_floating = (Button)   findViewById(R.id.btn_floating);
        btn_action = (Button)   findViewById(R.id.btn_action);

        btn_action.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                boolean res = false;
                if(mActionMode == null){
                    mActionMode = startSupportActionMode(mActionCallback);
                    res = true;
                }

                return res;
            }
        });



    }

    private ActionMode.Callback mActionCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_menu,menu);
            mode.setTitle("Action Menu");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            switch(itemId){
                case R.id.act_light_blue:
                    btn_action.setBackgroundColor(Color.CYAN);
                    mode.finish();

                    break;
                case R.id.act_purple:
                    btn_action.setBackgroundColor(Color.MAGENTA);
                    mode.finish();
                    break;
                case R.id.act_yellow:
                    btn_action.setBackgroundColor(Color.YELLOW);
                    mode.finish();
                    break;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };


    /**
     * Método que encapsula la acción Toast, evita tener que repetir código en los parámetros que
     * nunca cambian, como puede ser la duración, el contexto o que se olvide llamar al método show()
     * @param msg Mensaje a mostrar en el toast.
     */
    public void myToast(String msg){

        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


}