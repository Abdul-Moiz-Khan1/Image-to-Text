package com.example.image_to_text

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.image_to_text.databinding.ActivitySplashScreenBinding

class Splash_Screen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.lottie.setAnimation(R.raw.image_to_text)
        binding.lottie.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        },3000)

    }
}