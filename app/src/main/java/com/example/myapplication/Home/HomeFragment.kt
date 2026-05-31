package com.example.myapplication.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.BaseActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.Home.pertemuan_10.TenthActivity
import com.example.myapplication.Home.pertemuan_5.WebViewActivity
import com.example.myapplication.Home.pertemuan_7.SeventhActivity
import com.example.myapplication.Home.pertemuan_9.NinthActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Tombol 1
        binding.btn1.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Tombol 1 Ditekan",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Snackbar
        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Ini Snackbar",
                Snackbar.LENGTH_SHORT
            ).show()
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

        // WebView
        binding.btnWebView.setOnClickListener {

            val intent = Intent(
                requireContext(),
                WebViewActivity::class.java
            )

            startActivity(intent)
        }

        // Pertemuan 7
        binding.btnPertemuan7.setOnClickListener {

            val intent = Intent(
                requireContext(),
                SeventhActivity::class.java
            )

            startActivity(intent)
        }

        // Base Activity
        binding.btnBaseActivity.setOnClickListener {

            val intent = Intent(
                requireContext(),
                BaseActivity::class.java
            )

            startActivity(intent)
        }

        // Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {

            val intent = Intent(
                requireContext(),
                NinthActivity::class.java
            )

            startActivity(intent)
        }

        // Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {

            val intent = Intent(
                requireContext(),
                TenthActivity::class.java
            )

            startActivity(intent)
        }

        // Ke MainActivity
        binding.btnSubmit2.setOnClickListener {

            val intent = Intent(
                requireContext(),
                MainActivity::class.java
            )

            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}