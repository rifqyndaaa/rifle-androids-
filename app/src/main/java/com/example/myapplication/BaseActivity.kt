package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Home.HomeFragment
import com.example.myapplication.Message.MessageFragment
import com.example.myapplication.More.MoreFragment
import com.example.myapplication.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar tanpa back button & tanpa title
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = ""          // ❌ hilangkan "Base Activity"
            setDisplayHomeAsUpEnabled(false) // ❌ hilangkan tombol back
        }

        // Fragment default
        replaceFragment(HomeFragment())

        // Bottom Navigation
        binding.bottomNavView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.message -> {
                    replaceFragment(MessageFragment())
                    true
                }

                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }

                else -> false
            }
        }
    }

    // Replace Fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}