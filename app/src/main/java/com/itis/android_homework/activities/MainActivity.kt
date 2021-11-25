package com.itis.android_homework.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import com.itis.android_homework.notifications.AlarmService
import com.itis.android_homework.R
import com.itis.android_homework.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var service: AlarmService? = null
    private var calendar: Calendar?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        service = AlarmService(this)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.apply {
            btnSetTime.setOnClickListener {
                showTimePicker()
            }
            btnStart.setOnClickListener {
                start(calendar)
            }
            btnStop.setOnClickListener {
                stop()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }

    private fun start(calendar: Calendar?){
        calendar?.also{
            service?.setAlarm(
                it[Calendar.HOUR_OF_DAY],
                it[Calendar.MINUTE]
            )
        }
        showMessage("Alarm created.")
    }

    private fun stop(){
        service?.stopAlarm()
        clear()
        showMessage("Alarm canceled.")
    }

    private fun showTimePicker(){
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText(R.string.set_time_text)
            .build()
        with(timePicker){
            show(supportFragmentManager, "TimePicker")
            addOnPositiveButtonClickListener {
                calendar = Calendar.getInstance().also {
                    it[Calendar.HOUR_OF_DAY] = timePicker.hour
                    it[Calendar.MINUTE] = timePicker.minute
                }
                binding.apply {
                    setData(timePicker)
                }
            }
        }
    }

    private fun setData(timePicker: MaterialTimePicker) {
        binding.apply {
            val hours =
                if(timePicker.hour < 10) "0${timePicker.hour}"
                else timePicker.hour.toString()
            val minutes =
                if(timePicker.minute < 10) "0${timePicker.minute}"
                else timePicker.minute.toString()
            tvHours.text = hours
            tvMinutes.text = minutes
            tvHours.visibility = View.VISIBLE
            tvMinutes.visibility = View.VISIBLE
            tvDelimiter.visibility = View.VISIBLE
        }
    }

    private fun clear(){
        binding.apply {
            tvHours.visibility = View.GONE
            tvMinutes.visibility = View.GONE
            tvDelimiter.visibility = View.GONE
        }
    }

    private fun showMessage(text: String){
        binding.let {
            Snackbar.make(
                it.root,
                text,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
