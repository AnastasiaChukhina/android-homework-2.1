package com.itis.android_homework.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.core.app.NotificationCompat
import com.itis.android_homework.R
import com.itis.android_homework.activities.SecondActivity

private const val CHANNEL_ID = "channel_id_1"
private val pattern = arrayOf(100L, 200L, 0, 400L).toLongArray()

class AlarmReceiver: BroadcastReceiver() {

    private var manager : NotificationManager? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        manager = getNotificationManager(context)
        createNotificationChannel(context, manager)

        val intent = getIntent(context)
        build(intent, context)
    }

    private fun build(intent: PendingIntent, context: Context?) {
        val builder = context?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_outline_access_time_24)
                .setContentTitle(it.getString(R.string.alarm))
                .setShowWhen(false)
                .setAutoCancel(true)
                .setContentIntent(intent)
                .setContentText(it.getString(R.string.wake_up))
        }
        manager?.notify(1, builder?.build())
    }

    private fun getNotificationManager(
        context: Context?
    ): NotificationManager=
        context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun setAudioAttributes(): AudioAttributes? {
        return AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
    }

    private fun createNotificationChannel(context: Context?, manager: NotificationManager?){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context?.getString(R.string.alarm),
                IMPORTANCE_DEFAULT
            ).apply {
                description = context?.getString(R.string.wake_up)
                lightColor = Color.BLUE
                vibrationPattern = pattern

                val sound: Uri? = context?.getSoundUri(R.raw.sound)
                setSound(sound, setAudioAttributes())
            }.also {
                manager?.createNotificationChannel(it)
            }
        }
    }

    private fun getIntent(context: Context?): PendingIntent{
        return Intent(context, SecondActivity::class.java).let {
            PendingIntent.getActivities(
                context,
                1,
                arrayOf(it),
                PendingIntent.FLAG_ONE_SHOT
            )
        }
    }

    private fun Context.getSoundUri(
        @RawRes id: Int
    ) = Uri.parse("android.resource://${packageName}/$id")
}
