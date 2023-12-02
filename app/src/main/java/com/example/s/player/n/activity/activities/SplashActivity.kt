package com.example.s.player.n.activity.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.s.player.R
import com.example.s.player.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor= ContextCompat.getColor(this, R.color.youtube_color)

        Handler(Looper.getMainLooper()).postDelayed({
            callNextActivity()
        }, 4000)
    }

    private fun callNextActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}