package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var sharedPref: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Jika sudah login langsung ke MainActivity
        if (sharedPref.getBoolean("isLogin", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {

            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (username == password && username.isNotEmpty()) {

                // Simpan status login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {

                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username dan password harus sama dan tidak boleh kosong!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}