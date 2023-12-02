package com.example.s.player.n.activity.activities.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.s.player.databinding.ActivityPlaylistDetailsBinding
import com.example.s.player.databinding.AddPlaylistDialogBinding

class PlaylistDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlaylistDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        binding.title.text = title


    }
}