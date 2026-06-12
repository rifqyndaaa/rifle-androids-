package com.example.myapplication.Home.pertemuan_13

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityThirteenthBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = "Pertemuan 13"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewPager.adapter =
            ThirteenthTabsAdapter(this)

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->

            when (position) {

                0 -> tab.text = "Capture"

                1 -> tab.text = "Scan"

                2 -> tab.text = "QR Code"
            }

        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}