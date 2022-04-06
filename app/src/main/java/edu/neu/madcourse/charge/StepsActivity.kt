package edu.neu.madcourse.charge

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import edu.neu.madcourse.charge.databinding.ActivityStepsBinding

class StepsActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding : ActivityStepsBinding
    private var sensorManager: SensorManager? = null
    private var walking = false
    private var lifetimeSteps = 0
    private var deviceSteps = 0
    private var currentSteps = 0
    private lateinit var auth: FirebaseAuth
    private lateinit var user : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set custom toolbar
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar_custom)
        val toolbar = findViewById<TextView>(R.id.custom_toolbar)
        toolbar.text = getString(R.string.my_steps)

        // Get current user
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser.toString()

        // Set up the sensor using STEP_COUNTER
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val stepSensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        // Start step count
        binding.buttonStartSteps.setOnClickListener {
            walking = true
            if(stepSensor == null) {
                Toast.makeText(this, "Unable to count steps on this device", Toast.LENGTH_SHORT).show()
            } else {
                sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            }
        }

        // Save and reset step count
        binding.buttonSaveSteps.setOnClickListener {
            walking = false

            if(stepSensor != null) {
                sensorManager?.unregisterListener(this, stepSensor)
            }
        }
    }

    override fun onSensorChanged(step: SensorEvent?) {
        if(walking) {
            step!!.values[0].toInt().also { currentSteps = it }

            binding.textViewCurrentCount.text = ("$currentSteps")
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }
}
