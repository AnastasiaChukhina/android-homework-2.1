package com.itis.android_homework.services

import android.app.NotificationManager
import android.content.Context

private const val CHANNEL_ID = "song_channel"

class NotificationService(
    private val context: Context
) {

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}
