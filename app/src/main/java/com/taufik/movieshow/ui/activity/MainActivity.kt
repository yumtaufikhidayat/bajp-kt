package com.taufik.movieshow.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taufik.movieshow.databinding.ActivityMainBinding
import com.taufik.movieshow.ui.adapter.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        setViewPager()
    }

    private fun initActionBar() {
        supportActionBar?.elevation = 0F
    }

    private fun setViewPager() {
        val mainPagerAdapter = MainPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPagerMain.adapter = mainPagerAdapter
            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }
    }
}