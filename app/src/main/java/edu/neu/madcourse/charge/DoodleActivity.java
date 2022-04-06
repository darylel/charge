package edu.neu.madcourse.charge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class DoodleActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener sensorEventListener;
    private double currentAcceleration;
    private double previousAcceleration;
    private DoodleCanvas doodleCanvas;
    private static final String KEY_PAINT = "paint_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doodleCanvas = new DoodleCanvas(this);
        setContentView(doodleCanvas);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        TextView toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setText("Doodle");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float currentX = sensorEvent.values[0];
                float currentY = sensorEvent.values[1];
                float currentZ = sensorEvent.values[2];

                currentAcceleration = Math.sqrt((currentX * currentX + currentY * currentY + currentZ * currentZ));
                double shakeValue = Math.abs(currentAcceleration - previousAcceleration);
                previousAcceleration = currentAcceleration;

                if (shakeValue > 10) {
                    doodleCanvas = new DoodleCanvas(getApplicationContext());
                    setContentView(doodleCanvas);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}