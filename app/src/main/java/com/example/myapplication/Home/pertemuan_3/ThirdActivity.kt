package com.example.myapplication.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityThirdBinding
import com.example.myapplication.utils.NotificationHelper
import com.example.myapplication.utils.PermissionHelper

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    // Launcher untuk menangani callback permission dari user
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Setup Toolbar ---
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Third"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // --- Cek & Minta Izin Notifikasi (Android 13 ke atas) ---
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // --- Action Tombol Kirim ---
        // PENTING: Pastikan ID di XML kamu adalah btnkirim atau btnKirim.
        // Di bawah ini disesuaikan dengan kode pertama kamu: btnkirim
        binding.btnkirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text.toString().trim()

            if (noTujuan.isEmpty()) {
                Toast.makeText(
                    this,
                    "Silakan masukkan nomor tujuan",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Nomor yang dimasukkan: $noTujuan",
                    Toast.LENGTH_SHORT
                ).show()

                // Menyiapkan Intent menuju ThirdResultActivity
                val intent = Intent(this, ThirdResultActivity::class.java)
                intent.putExtra("nomor", noTujuan)

                // Memicu Local Notification menggunakan Helper
                NotificationHelper.showNotification(
                    this,
                    "Pesanan Anda",
                    "Halo $noTujuan, Pesanan Anda Sedang Diproses",
                    intent
                )


                // --- Action Tombol Kirim ---
                binding.btnkirim.setOnClickListener {
                    val noTujuan = binding.inputNoTujuan.text.toString().trim()

                    if (noTujuan.isEmpty()) {
                        Toast.makeText(
                            this,
                            "Silakan masukkan nomor tujuan",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Nomor yang dimasukkan: $noTujuan",
                            Toast.LENGTH_SHORT
                        ).show()

                        // 1. Menyiapkan Intent menuju ThirdResultActivity
                        val intent = Intent(this, ThirdResultActivity::class.java)
                        intent.putExtra("nomor", noTujuan)

                        // 2. PINDAH HALAMAN LANGSUNG (Diaktifkan kembali)
                        startActivity(intent)

                        // 3. MEMICU NOTIFIKASI
                        NotificationHelper.showNotification(
                            this,
                            "Pesanan Anda",
                            "Halo $noTujuan, Pesanan Anda Sedang Diproses",
                            intent
                        )
                    }
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}