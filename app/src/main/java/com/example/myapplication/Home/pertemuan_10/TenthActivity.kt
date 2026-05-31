package com.example.myapplication.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Pertemuan 10"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // Set Adapter
        binding.viewPager.adapter = tabsAdapter

        // Hubungkan TabLayout dan ViewPager
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->

            when(position){

                0 -> tab.text = "Tab A"

                1 -> tab.text = "Tab B"

                2 -> tab.text = "Produk"
            }

        }.attach()
    }
}