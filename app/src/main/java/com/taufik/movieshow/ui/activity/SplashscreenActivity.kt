package com.taufik.movieshow.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taufik.movieshow.databinding.ActivitySplashscreenBinding

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}