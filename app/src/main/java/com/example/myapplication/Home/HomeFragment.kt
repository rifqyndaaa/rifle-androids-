package com.example.myapplication.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BaseActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.Home.pertemuan_3.ThirdActivity
import com.example.myapplication.Home.pertemuan_10.TenthActivity
import com.example.myapplication.Home.pertemuan_13.ThirteenthActivity
import com.example.myapplication.Home.pertemuan_5.WebViewActivity
import com.example.myapplication.Home.pertemuan_7.SeventhActivity
import com.example.myapplication.Home.pertemuan_9.NinthActivity
import com.example.myapplication.Home.photo.PhotoAdapter
import com.example.myapplication.data.api.CatFactApiClient
import com.example.myapplication.data.api.PhotoApiClient
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlin.jvm.java

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Memuat data awal saat Fragment pertama kali terbuka
        loadCatFact()
        loadPhoto()

        // Pindah ke sini sesuai instruksi Tahap 11
        binding.btnPertemuan13.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    ThirteenthActivity::class.java
                )
            )
        }

        // Setup action listener tombol lainnya
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Tombol 1
        binding.btn1.setOnClickListener {
            Toast.makeText(requireContext(), "Tombol 1 Ditekan", Toast.LENGTH_SHORT).show()
        }

        // Snackbar
        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini Snackbar", Snackbar.LENGTH_SHORT).show()
        }

        // Alert Dialog
        binding.btnShowAlertDialog.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Alert Dialog")
                .setMessage("Ini contoh Alert Dialog")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        binding.btnPertemuan3.setOnClickListener {
            startActivity(
                Intent(requireContext(), ThirdActivity::class.java)
            )
        }

        // WebView
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Pertemuan 7
        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // Base Activity
        binding.btnBaseActivity.setOnClickListener {
            startActivity(Intent(requireContext(), BaseActivity::class.java))
        }

        // Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // Main Activity
        binding.btnSubmit2.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        // Tombol Refresh API
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
            loadPhoto() // Jika ingin tombol refresh turut memperbarui galeri foto
        }
    }

    private fun loadCatFact() {
        binding.tvCatFact.text = "Loading cat fact..."

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = response.fact
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Grid */
                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}