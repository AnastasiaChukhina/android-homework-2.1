package com.itis.android_homework.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.itis.android_homework.notifications.AlarmService
import com.itis.android_homework.R
import com.itis.android_homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var service: AlarmService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        service = AlarmService(this)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding?.apply {
            btnStart.setOnClickListener {
                start(
                    etHours.text.toString(),
                    etMinutes.text.toString()
                )
            }
            btnStop.setOnClickListener {
                stop()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
        binding = null
    }

    private fun start(hours: String, minutes: String){
        if(hours.isNotEmpty() && minutes.isNotEmpty()) {
            val hh = hours.toInt()
            val mm = minutes.toInt()
            if (hh > 23 || hh < 0 || mm > 59 || mm < 0)
                showMessage("Please, set correct time.")
            else {
                service?.setAlarm(hours.toInt(), minutes.toInt())
                showMessage("Alarm created.")
            }
        }
        else showMessage("Time is empty.")
    }

    private fun stop(){
        service?.stopAlarm()
    }

    private fun showMessage(text: String){
        binding?.let {
            Snackbar.make(
                it.root,
                text,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
