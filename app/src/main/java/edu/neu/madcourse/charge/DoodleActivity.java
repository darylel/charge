package edu.neu.madcourse.charge;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class DoodleActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener sensorEventListener;
    private double currentAcceleration;
    private double previousAcceleration;
    private DoodleCanvas doodleCanvas;

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

        // Snackbar to let users know to shake to erase
        Snackbar.make(doodleCanvas, "Shake to erase your doodle", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", view -> {
                    // Dismiss SnackBar
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.charge_off_white))
                .show();

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