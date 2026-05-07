package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            delay(2000)

            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

            // reset login setiap aplikasi dibuka
            sharedPref.edit().clear().apply()

            startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
            finish()
        }
    }
}