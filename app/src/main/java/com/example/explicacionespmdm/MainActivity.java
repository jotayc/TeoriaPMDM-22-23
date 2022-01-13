package com.example.explicacionespmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad Sensores: Proyecto de ejemplo donde se muestran los diferentes sensores, su acceso y uso.
 * @author JC
 * @version 0.1
 *
 *  Consideraciones previas:
 *  1. Dependiendo del sensor puede que necesitemos dar permisos en el manifest
 *  2. Se debe comprobar que el dispositivo tenga los sensores antes de acceder a ellos.
 *
 *  3. Documentación oficial:
 *     Sensores (General) -> https://developer.android.com/guide/topics/sensors?hl=es
 *     SensorEvent(Clase) -> https://developer.android.com/reference/android/hardware/SensorEvent
 *
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView tvAcc;
    private TextView tvLight;
    private TextView tvList;

    private Button btnShow;
    private List<Sensor> deviceSensors;

    private Sensor mLight;
    private Sensor mAccelometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAcc = (TextView) findViewById(R.id.txtVw_ac);
        tvLight = (TextView) findViewById(R.id.txtVw_light);
        tvList = (TextView) findViewById(R.id.txtVw_list);
        btnShow = (Button) findViewById(R.id.btn_show);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mAccelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvList.setText(deviceSensors.toString());
            }
        });



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                    tvAcc.setText("x: "+sensorEvent.values[0]+"\n"+
                                    "y: "+sensorEvent.values[1]+"\n"+
                                    "z: "+sensorEvent.values[2]+"\n");
                break;
            case Sensor.TYPE_LIGHT:
                tvLight.setText(""+sensorEvent.values[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mLight != null)
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);

        if(mAccelometer != null)
            sensorManager.registerListener(this, mAccelometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}