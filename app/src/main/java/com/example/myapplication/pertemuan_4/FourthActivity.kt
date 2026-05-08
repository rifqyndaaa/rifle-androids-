package com.example.myapplication.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFourthBinding
import com.example.myapplication.pertemuan_5.WebViewActivity
import com.example.myapplication.pertemuan_7.SeventhActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge to Edge padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Fourth Activity"
            subtitle = "Ini adalah subtitle"

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Ambil data intent
        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age = intent.getIntExtra("age", 0)

        Log.e("Data Intent", "Nama: $name , Usia: $age, Asal: $from")

        // Ke MainActivity
        binding.btnSubmit2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Snackbar
        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Ini adalah Snackbar",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Tutup") {
                    finish()
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        // Alert Dialog
        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }

        // WebViewActivity
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        // 👉 PERTEMUAN 7
        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(this, SeventhActivity::class.java))
        }

        Log.e("onCreate", "FourthActivity dibuat pertama kali")
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }
}