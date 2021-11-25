package com.itis.android_homework.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmService(
    private val context: Context
) {

    private val manager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private val intent by lazy{
        Intent(context, AlarmReceiver::class.java).let {
            PendingIntent.getBroadcast(
                context,
                0,
                it,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    fun setAlarm(hours: Int, minutes: Int){
        val calendar = setTime(hours, minutes)
        manager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, intent)
    }

    fun stopAlarm(){
        manager.cancel(intent)
    }

    private fun setTime(
        hours: Int,
        minutes: Int
    ): Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, minutes)
    }
}
