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
import com.example.myapplication.utils.PermissionHelper
import com.example.myapplication.utils.ReminderHelper
import java.util.Calendar

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
        binding.btnkirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text.toString().trim()

            if (noTujuan.isEmpty()) {
                Toast.makeText(
                    this,
                    "Silakan masukkan nomor tujuan",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // 1. Mengambil instance waktu sekarang dan menambahkan 1 menit ke depan untuk alarm
                val calendar = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, 1)
                }

                // 2. Memasang alarm/reminder menggunakan ReminderHelper
                ReminderHelper.setReminder(
                    context = this,
                    hour = calendar.get(Calendar.HOUR_OF_DAY),
                    minute = calendar.get(Calendar.MINUTE),
                    title = "Reminder 1 Menit",
                    message = "Halo $noTujuan, reminder ini muncul 1 menit setelah tombol ditekan",
                    targetActivity = ThirdResultActivity::class.java
                )

                // 3. Pindah Halaman ke ThirdResultActivity dengan membawa data nomor tujuan
                val intent = Intent(this, ThirdResultActivity::class.java).apply {
                    putExtra("nomor", noTujuan)
                }
                startActivity(intent)

                Toast.makeText(
                    this,
                    "Data dikirim! Silahkan tunggu 1 Menit untuk menerima Notifikasi...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}