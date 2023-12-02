package com.example.s.player.n.activity.activities

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.util.TimeUtils.formatDuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.s.player.R
import com.example.s.player.databinding.ActivityPlayerBinding
import com.example.s.player.n.activity.MusicService
import com.example.s.player.n.activity.activities.MainActivity.Companion.MusicListMA
import com.example.s.player.n.activity.fragments.FavouriteFragment
import com.example.s.player.n.activity.models.Music
import com.example.s.player.n.activity.models.favouriteChecker
import com.example.s.player.n.activity.models.musicFormatDuration
import com.example.s.player.n.activity.widgets.PlayPauseDrawable
import java.lang.Exception

class PlayerActivity : AppCompatActivity(), ServiceConnection {

    companion object {
        lateinit var musicListPA: ArrayList<Music>
        var songPosition: Int = 0
//        var mediaPlayer: MediaPlayer? = null

        var isPlaying = false
        var musicService: MusicService? = null

        var sortOrder: Int = 0
        var isFavourite: Boolean = false
        var fIndex: Int = -1

        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivityPlayerBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        musicListPA = ArrayList()
        musicListPA.addAll(MainActivity.MusicListMA)
        initializeLayout()
        setLayout()
        createMediaPlayer()

        //for start service
        val serviceIntent = Intent(this, MusicService::class.java)
        bindService(serviceIntent, this, BIND_AUTO_CREATE)
        startService(serviceIntent)

        val songTitle = intent.getStringExtra("title")
        songPosition = intent.getIntExtra("index", 0)
        binding.songTitle.text = songTitle
//        if (mediaPlayer == null) mediaPlayer = MediaPlayer()
//        mediaPlayer!!.reset()
//        mediaPlayer!!.setDataSource(musicListPA[songPosition].path)
//        mediaPlayer!!.prepare()
//        mediaPlayer!!.start()
//        isPlaying = true
//        binding.playPause.setImageResource(R.drawable.pause)
        binding.playPause.setOnClickListener {
            if (isPlaying) {
                pauseMusic()
            } else {
                playMusic()
            }
        }
        binding.next.setOnClickListener {
            playPrevNextSong(true)
        }
        binding.previous.setOnClickListener {
            playPrevNextSong(false)
        }

        binding.down.setOnClickListener {
            super.onBackPressed()
        }

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    musicService!!.mediaPlayer!!.seekTo(progress)
//                    musicService!!.showNotification(if(isPlaying) R.drawable.pause_icon else R.drawable.play_icon)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

        binding.favourite.setOnClickListener {
            if (isFavourite) {
                binding.favourite.setImageResource(R.drawable.love_outline)
                isFavourite = false
                FavouriteFragment.fabSongs.removeAt(fIndex)
            } else {
                binding.favourite.setImageResource(R.drawable.heart_fill_green)
                isFavourite = true
                FavouriteFragment.fabSongs.add(musicListPA[songPosition])
            }

        }

    }

    @SuppressLint("CheckResult")
    private fun setLayout() {
        fIndex = favouriteChecker(musicListPA[songPosition].id)
        Glide.with(applicationContext)
            .load(musicListPA[songPosition].artist)
            .apply(RequestOptions().placeholder(R.drawable.music_note).centerCrop())
//            .into(binding.artistImg)
        binding.songTitle.text = musicListPA[songPosition].title
        if (isFavourite) {
            binding.favourite.setImageResource(R.drawable.heart_fill_green)
        } else {
            binding.favourite.setImageResource(R.drawable.love_outline)
        }
    }

    private fun initializeLayout() {
        songPosition = intent.getIntExtra("index", 0)

        when (intent.getStringExtra("class")) {
            "MusicAdapter" -> {
                musicListPA = ArrayList()
                musicListPA.addAll(MainActivity.MusicListMA)
//                setLayout()
            }

            "MainActivity" -> {
                musicListPA = ArrayList()
                musicListPA.addAll(MainActivity.MusicListMA)
                musicListPA.shuffle()
//                setLayout()
            }


        }

        val sortEditor = getSharedPreferences("SORTING", MODE_PRIVATE)
        sortOrder = sortEditor.getInt("sortOrder", 0)

        //for refreshing layout on swipe from top
//        binding.refreshLayout.setOnRefreshListener {
//            MusicListMA = getAllAudio()
//            musicAdapter.updateMusicList(MusicListMA)
//
//            binding.refreshLayout.isRefreshing = false
//        }
    }

    private fun playPrevNextSong(increment: Boolean) {
        if (increment) {
            setSongPosition(increment = true)
            createMediaPlayer()
        } else {
            setSongPosition(increment = false)
            createMediaPlayer()
        }

    }

//    private fun createMediaPlayer() {
////        musicListPA = ArrayList()
//        musicListPA.addAll(MainActivity.MusicListMA)
//        try {
//            if (musicService!!.mediaPlayer == null) {
//                musicService!!.mediaPlayer = MediaPlayer()
//                musicService!!.mediaPlayer!!.reset()
//                musicService!!.mediaPlayer!!.setDataSource(musicListPA[songPosition].path)
//                musicService!!.mediaPlayer!!.prepare()
//                musicService!!.mediaPlayer!!.start()
////                isPlaying = true
//                binding.playPause.setImageResource(R.drawable.pause)
//                binding.songDurationDecrease.text =
//                    musicFormatDuration(musicService!!.mediaPlayer!!.currentPosition.toLong())
//                binding.songDuration.text =
//                    musicFormatDuration(musicService!!.mediaPlayer!!.duration.toLong())
//                binding.seekbar.progress = 0
//                binding.seekbar.max = musicService!!.mediaPlayer!!.duration
//                binding.songDurationDecrease.text =
//                    musicFormatDuration(musicService!!.mediaPlayer!!.duration.toLong())
//                binding.songDuration.text
//
//            }
//
//        } catch (e: Exception) {
//        }
//
//        binding.playPause.setOnClickListener {
//            if (isPlaying) {
//                pauseMusic()
//            } else {
//                playMusic()
//            }
//        }
//        binding.next.setOnClickListener { playPrevNextSong(increment = false) }
//        binding.previous.setOnClickListener { playPrevNextSong(increment = false) }
//
//
//    }


    private fun createMediaPlayer() {
        try {
            if (musicService!!.mediaPlayer == null) musicService!!.mediaPlayer = MediaPlayer()
            musicService!!.mediaPlayer!!.reset()
            musicService!!.mediaPlayer!!.setDataSource(musicListPA[songPosition].path)
            musicService!!.mediaPlayer!!.prepare()
            binding.seekbar.max = musicService!!.mediaPlayer!!.duration
            playMusic()
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun playMusic() {
        isPlaying = true
        musicService!!.mediaPlayer!!.start()
        binding.playPause.setImageResource(R.drawable.pause)
//        mediaPlayer!!.start()
    }


    private fun pauseMusic() {
        isPlaying = false
        musicService!!.mediaPlayer!!.pause()
        binding.playPause.setImageResource(R.drawable.play_arrow)
//        mediaPlayer!!.pause()
    }

    private fun setSongPosition(increment: Boolean) {
        if (increment) {
            if (musicListPA.size - 1 == songPosition) {
                songPosition = 0
            } else {
                ++songPosition
            }
        } else {
            if (0 == songPosition) {
                songPosition = musicListPA.size - 1
            } else --songPosition
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.player_act_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }

            R.id.menu_settings -> {}
            R.id.menu_settings -> {}


        }
        return super.onOptionsItemSelected(item)
    }


    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        val binder = p1 as MusicService.MyBinder
//        musicService = binder.currentService()
//        createMediaPlayer()
//        musicService!!.showNotification()
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        musicService = null
    }


}
