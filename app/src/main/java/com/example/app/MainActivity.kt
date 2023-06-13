package com.example.app

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.text.set
import com.example.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var binding : ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private var mAccel : Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editText1.setText("0.0")
        binding.editText2.setText("0.0")
        binding.editText3.setText("0.0")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            var x = event.values[0]
            var y = event.values[1]
            var z = event.values[2]
            binding.editText1.setText(x.toString())
            binding.editText2.setText(y.toString())
            binding.editText3.setText(z.toString())
            if (x > 20.0 || y > 20.0 || z > 20.0) {
                var intent = Intent(this, DisplayActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onResume() {
        super.onResume()
        mAccel?.also { light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}
