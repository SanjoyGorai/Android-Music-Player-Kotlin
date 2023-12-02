package com.example.s.player.n.activity

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.MediaSessionCompat.Token
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.*
import com.example.s.player.R
import com.example.s.player.n.activity.activities.PlayerActivity

class MusicService : Service() {
    private var myBinder = MyBinder()
    var mediaPlayer: MediaPlayer? = null
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var runnable: Runnable

    override fun onBind(intent: Intent?): IBinder {
        mediaSession = MediaSessionCompat(baseContext, "session")
        return myBinder
    }

    inner class MyBinder : Binder() {
        fun currentService(): MusicService {

            return MusicService()
        }
    }

    fun showNotification() {

//        val prevIntent = Intent(baseContext,NotificationReceiver::class.java)
//            .setAction(MyApplicationClass.PREVIOUS)
//        val prevPendingIntent = PendingIntent
//            .getBroadcast(baseContext,0,prevIntent,PendingIntent.FLAG_UPDATE_CURRENT)

//        val nextIntent = Intent(baseContext,NotificationReceiver::class.java).setAction(MyApplicationClass.NEXT)
//        val nextPendingIntent = PendingIntent
//            .getBroadcast(baseContext,0,nextIntent,PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val playIntent = Intent(baseContext,NotificationReceiver::class.java).setAction(MyApplicationClass.PLAY)
//        val playPendingIntent = PendingIntent
//            .getBroadcast(baseContext,0,playIntent,PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val exitIntent = Intent(baseContext,NotificationReceiver::class.java).setAction(MyApplicationClass.EXIT)
//        val exitPendingIntent = PendingIntent
//            .getBroadcast(baseContext,0,exitIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(baseContext, MyApplicationClass.CHANNEL_ID)
            .setContentTitle(PlayerActivity.musicListPA[PlayerActivity.songPosition].title)
            .setContentTitle(PlayerActivity.musicListPA[PlayerActivity.songPosition].artist)
            .setSmallIcon(R.drawable.baseline_notifications_24)
//            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.spotify))
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.outline_skip_previous, "Prev", null)
            .addAction(R.drawable.play_arrow, "Prev", null)
            .addAction(R.drawable.sharp_next, "Prev", null)
            .addAction(R.drawable.close, "Prev", null)
            .build()

        startForeground(2, builder)

    }
    fun seekBarSetUp() {
        runnable = Runnable {
            PlayerActivity.binding.songDuration.text 
        }
    }
}