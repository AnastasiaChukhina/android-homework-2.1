package com.itis.android_homework.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.repositories.SongRepository
import androidx.media.app.NotificationCompat.MediaStyle as NotificationCompatMedia

const val ACTION_PREV = "PREVIOUS"
const val ACTION_PAUSE = "PAUSE"
const val ACTION_PLAY = "PLAY"
const val ACTION_STOP = "STOP"
const val ACTION_NEXT = "NEXT"
private const val CHANNEL_ID = "song_channel"
private const val NOTIFICATION_ID = 0

class NotificationService(
    private val context: Context
) {
    private var prevPendingIntent: PendingIntent? = null
    private var pausePendingIntent: PendingIntent? = null
    private var nextPendingIntent: PendingIntent? = null
    private var stopPendingIntent: PendingIntent? = null
    private var playPendingIntent: PendingIntent? = null

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.music_channel_name),
                IMPORTANCE_DEFAULT
            ).apply{
                description = context.getString(R.string.music_channel_desc)
            }.also {
                manager.createNotificationChannel(it)
            }

            val prevIntent = Intent(context, MusicService::class.java).apply {
                action = ACTION_PREV
            }
            val pauseIntent = Intent(context, MusicService::class.java).apply {
                action = ACTION_PAUSE
            }
            val playIntent = Intent(context, MusicService::class.java).apply {
                action = ACTION_PLAY
            }
            val nextIntent = Intent(context, MusicService::class.java).apply {
                action = ACTION_NEXT
            }
            val stopIntent = Intent(context, MusicService::class.java).apply {
                action = ACTION_STOP
            }
            prevPendingIntent = PendingIntent.getService(context, 1, prevIntent, 0)
            pausePendingIntent = PendingIntent.getService(context, 2, pauseIntent, 0)
            nextPendingIntent = PendingIntent.getService(context, 3, nextIntent, 0)
            stopPendingIntent = PendingIntent.getService(context, 4, stopIntent, 0)
            playPendingIntent = PendingIntent.getService(context, 5, playIntent, 0)
        }
    }

    fun buildNotification(id: Int) {
        val song = SongRepository.songs[id]

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    song.cover
                )
            )
            .addAction(R.drawable.ic_baseline_fast_rewind_24, "Previous", prevPendingIntent)
            .addAction(R.drawable.ic_baseline_pause_24, "Pause", pausePendingIntent)
            .addAction(R.drawable.ic_baseline_stop_24, "Stop", stopPendingIntent)
            .addAction(R.drawable.ic_baseline_fast_forward_24, "Next", nextPendingIntent)
            .setContentTitle(song.title)
            .setContentText(song.author)
            .setStyle(NotificationCompatMedia())
            .setSilent(true)
            .setContentIntent(setNotificationAction(id))

        manager.notify(NOTIFICATION_ID, builder.build())
    }

    fun rebuildNotification(id: Int) {
        val song = SongRepository.songs[id]

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    song.cover
                )
            )
            .addAction(R.drawable.ic_baseline_fast_rewind_24, "Previous", prevPendingIntent)
            .addAction(R.drawable.ic_baseline_play_arrow_24, "Play", playPendingIntent)
            .addAction(R.drawable.ic_baseline_stop_24, "Stop", stopPendingIntent)
            .addAction(R.drawable.ic_baseline_fast_forward_24, "Next", nextPendingIntent)
            .setContentTitle(song.title)
            .setContentText(song.author)
            .setStyle(NotificationCompatMedia())
            .setSilent(true)
            .setContentIntent(setNotificationAction(id))

        manager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun setNotificationAction(id: Int): PendingIntent{
        val bundle = Bundle()
        bundle.putInt("ARG_SONG_ID", id)

        return NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.songFragment)
            .setArguments(bundle)
            .createPendingIntent()
    }
}
