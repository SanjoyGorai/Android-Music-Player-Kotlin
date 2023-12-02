package com.example.s.player.n.activity

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplicationClass : Application() {
    companion object {
        const val CHANNEL_ID = "channel id"
        const val PLAY = "play id"
        const val PAUSE = "pause id"
        const val NEXT = "next id"
        const val PREVIOUS = "previous id"
        const val EXIT = "exit id"
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, "Now Playing", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.description = " This is a important channel for showing song"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


}