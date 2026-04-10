package com.example.myapplication.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnkirim.setOnClickListener {


            if (binding.inputNoTujuan.text.toString().isEmpty()) {
                Toast.makeText(
                    this,
                    "Anda telah melakukan klik pada tombol Submit",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                val nomor = binding.inputNoTujuan.text
                Toast.makeText(this, "nomor yg anda masukan $nomor", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ThirdResultActivity::class.java)
                startActivity(intent)
            }
        }
    }

}