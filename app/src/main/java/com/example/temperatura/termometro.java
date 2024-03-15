package com.example.temperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class termometro extends AppCompatActivity implements SensorEventListener {
    private TextView textTempView;
    private TextView falloText;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean sensorTempDisponible;
    private ProgressBar progressBar;
    private int temperatura =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termometro);


        textTempView= findViewById(R.id.textTempView);
        falloText= findViewById(R.id.falloText);
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        progressBar= (ProgressBar) findViewById(R.id.progressBar2);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){
            tempSensor= sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            sensorTempDisponible=true;
            falloText.setVisibility(View.GONE);
        }else{
            falloText.setText("Dispositivo sin sensor de temperatura");
            textTempView.setVisibility(View.GONE);
            sensorTempDisponible=false;
        }
        if(sensorTempDisponible){
            sensorManager.registerListener(this,tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    public void menu (View view) {
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        temperatura= (int) event.values[0];
        textTempView.setText(temperatura+" °C");
        progressBar.setProgress(temperatura);


        if (temperatura <= -150) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Rojo));

        } else if (temperatura <= -250) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Naranja));
        } else if (temperatura <= -200) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Amarrillo));
        } else if (temperatura <= -150) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Blanco));
        }else if (temperatura <= -100) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Verde));
        }else if (temperatura <= -50) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Azulado));
        }else if (temperatura <= 0) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Azul));
        }else if (temperatura <= 50) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Purpura));
        }else  {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Morado));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
