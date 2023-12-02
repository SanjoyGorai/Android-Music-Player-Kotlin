package com.example.s.player.n.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.s.player.R
import com.example.s.player.n.activity.activities.PlayerActivity
import kotlin.system.exitProcess

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        when (intent?.action) {
            MyApplicationClass.PREVIOUS -> {}
            MyApplicationClass.PLAY -> {
                if (PlayerActivity.isPlaying) {
                    pauseMusic()
                } else playMusic()
            }

            MyApplicationClass.NEXT -> {}
            MyApplicationClass.EXIT -> {
                PlayerActivity.musicService!!.stopForeground(true)
                PlayerActivity.musicService = null
                exitProcess(1)
            }

        }

    }

    private fun playMusic() {
        PlayerActivity.isPlaying = true
        PlayerActivity.musicService!!.mediaPlayer!!.start()
//        PlayerActivity.musicService!!.showNotification(R.drawable.pause)
        PlayerActivity.binding.playPause.setImageResource(R.drawable.pause)
    }

    private fun pauseMusic() {
        PlayerActivity.isPlaying = false
        PlayerActivity.musicService!!.mediaPlayer!!.pause()
//        PlayerActivity.musicService!!.showNotification(R.drawable.play_arrow)
        PlayerActivity.binding.playPause.setImageResource(R.drawable.play_arrow)
    }
}