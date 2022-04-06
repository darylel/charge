package edu.neu.madcourse.charge

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var walking = false
    private var lifetimeSteps = 0f
    private var currentSteps = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar_custom)

        val toolbar = findViewById<TextView>(R.id.custom_toolbar)
        toolbar.text = getString(R.string.my_steps)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        buttonStartSteps.setOnClickListener {
            walking = true
            val stepSensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

            if(stepSensor == null) {
                Toast.makeText(this, "Unable to count steps on this device", Toast.LENGTH_SHORT).show()
            } else {
                sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(walking) {
            event!!.values[0].toInt().also { currentSteps = it }
            textViewCurrentCount.text = ("$currentSteps")
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }
}