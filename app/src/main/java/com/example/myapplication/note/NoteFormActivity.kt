package com.example.myapplication.note

import AppDatabase
import NoteEntity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Catatan"
            setDisplayHomeAsUpEnabled(true)
        }

        // INIT DATABASE
        db = AppDatabase.getInstance(this)

        // BUTTON SAVE
        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            // versi seperti contoh kamu (isNotBlank)
            if (title.isNotBlank() && content.isNotBlank()) {

                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )

                    db.noteDao().insert(note)
                    finish()
                }

            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}