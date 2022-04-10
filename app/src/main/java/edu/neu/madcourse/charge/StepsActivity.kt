package edu.neu.madcourse.charge

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import edu.neu.madcourse.charge.databinding.ActivityStepsBinding

class StepsActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding : ActivityStepsBinding
    private var sensorManager: SensorManager? = null
    private var walking = false
    private var lifetimeSteps = 0
    private var previousSteps = 0
    private var deviceSteps = 0
    private var currentSteps = 0
    private lateinit var db: DatabaseReference
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
        user = auth.currentUser!!.uid

        // Load lifetime steps from database
        db = FirebaseDatabase.getInstance().getReference(user).child("steps")
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap: DataSnapshot in snapshot.children) {
                    if(snap.key.equals("total")) {
                        lifetimeSteps = snap.value.toString().toInt()
                    }

                    if(snap.key.equals("previous")) {
                        Log.i("INFO/LIFETIME", snap.value.toString())
                        deviceSteps = snap.value.toString().toInt()
                    }
                    // Display lifetime steps in the activity
                    binding.textViewTotalCount?.text= ("$lifetimeSteps")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        // Set up the sensor using STEP_COUNTER
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val stepSensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        // Start step count
        binding.buttonStartSteps?.setOnClickListener {
            startSteps(stepSensor)
        }

        // Save and reset step count
        binding.buttonSaveSteps?.setOnClickListener {
            saveSteps(stepSensor)
        }
    }

    override fun onSensorChanged(step: SensorEvent?) {
        if(walking) {
            Log.i("INFO/Step Value", step!!.values[0].toInt().toString())
            previousSteps = step.values[0].toInt()

            // Handle 0 in previous steps if sensor has been rebooted to 0 since last activity
            currentSteps = if(previousSteps > 0) {
                previousSteps - deviceSteps
            } else {
                previousSteps
            }

            // Update the current steps count in the display
            binding.textViewCurrentCount?.text = ("$currentSteps")
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }

    private fun startSteps(stepSensor: Sensor?) {
        walking = true
        if(stepSensor == null) {
            Toast.makeText(this, "Unable to count steps on this device", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    private fun saveSteps(stepSensor: Sensor?) {
        walking = false

        // Increment total steps with the current steps amount from this "session"
        db.child("total").setValue(ServerValue.increment(currentSteps.toLong()))
                .addOnCompleteListener { task ->
                    if(!task.isSuccessful) {
                        Toast.makeText(this@StepsActivity, "Unable to add steps",
                                    Toast.LENGTH_SHORT).show()
                    }
                }
        // Save current steps in sensor for next time activity is used
        db.child("previous").setValue(previousSteps.toLong())
                .addOnCompleteListener { task ->
                    if(!task.isSuccessful) {
                        Toast.makeText(this@StepsActivity, "Unable to add steps",
                                Toast.LENGTH_SHORT).show()
                    }
                }


        binding.textViewCurrentCount?.text = "0"

        if(stepSensor != null) {
            sensorManager?.unregisterListener(this, stepSensor)
        }
    }
}
