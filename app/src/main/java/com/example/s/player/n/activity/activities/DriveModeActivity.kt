package com.example.s.player.n.activity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.s.player.R
import com.example.s.player.databinding.ActivityDriveModeBinding

class DriveModeActivity : AppCompatActivity() {
    lateinit var binding: ActivityDriveModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriveModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}