package com.example.s.player.n.activity.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.s.player.R
import com.example.s.player.databinding.FragmentNowPlayingBinding
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.activities.QueueActivity
import com.example.s.player.n.activity.adapters.MusicAdapter

class NowPlayingFragment : Fragment() {
    private var _binding: FragmentNowPlayingBinding? = null
    private val binding: FragmentNowPlayingBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)

//        binding.songTitle.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
        if (PlayerActivity.isPlaying) {
//            binding.playPause.setImageResource(R.drawable.pause)
        } else {
//            binding.playPause.setImageResource(R.drawable.play_arrow)
        }
        binding.playPause.setOnClickListener {
            Toast.makeText(requireContext(), "Play Img Clicked", Toast.LENGTH_SHORT).show()
        }
//        binding.queuepa.setOnClickListener {
//            startActivity(Intent(requireContext(), QueueActivity::class.java))
//        }
        binding.favourite.setOnClickListener {
            if (PlayerActivity.isFavourite) {
                PlayerActivity.isFavourite = false
                binding.favourite.setImageResource(R.drawable.heart_fill_green)
            } else {
                PlayerActivity.isFavourite = true
                binding.favourite.setImageResource(R.drawable.love_outline)
            }
        }
        binding.root.setOnClickListener {
            val intent = Intent(requireContext(), PlayerActivity::class.java)
            startActivity(intent)
        }
        if (PlayerActivity.musicService != null) {
            binding.title.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
//            binding.play.setImageResource(R.drawable.pause)
        } else {
//            binding.play.setImageResource(R.drawable.play_arrow)
        }
        if (PlayerActivity.isPlaying) {
//            binding.playPause.setImageResource(R.drawable.pause)
        } else {
//            binding.playPause.setImageResource(R.drawable.play_arrow)
        }

        binding.playPause.setOnClickListener {
            if (PlayerActivity.isPlaying) {
                pauseMusic()
            } else {
                pauseMusic()
            }
        }


        binding.title.isSelected = true


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        if (PlayerActivity.musicService != null) {

        }
    }

    private fun playMusic() {
        PlayerActivity.musicService!!.mediaPlayer!!.start()
//        binding.playPause.setImageResource(R.drawable.pause)
//        PlayerActivity.musicService!!.showNotification(R.drawable.pause)
        PlayerActivity.binding.next.setImageResource(R.drawable.pause)
        PlayerActivity.isPlaying = true
    }

    private fun pauseMusic() {
        PlayerActivity.musicService!!.mediaPlayer!!.start()
//        binding.playPause.setImageResource(R.drawable.play_arrow)
//        PlayerActivity.musicService!!.showNotification(R.drawable.play)
        PlayerActivity.binding.next.setImageResource(R.drawable.pause)
        PlayerActivity.isPlaying = false
    }


}