package com.example.myapplication.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Data List dengan Deskripsi
    private val dataListWithDesc = listOf(
        mapOf(
            "title" to "Kotlin",
            "desc" to "Bahasa untuk Android modern"
        ),
        mapOf(
            "title" to "Java",
            "desc" to "Bahasa OOP yang populer"
        ),
        mapOf(
            "title" to "Python",
            "desc" to "Bahasa yang mudah dipahami"
        ),
        mapOf(
            "title" to "C++",
            "desc" to "Bahasa dengan performa tinggi"
        ),
        mapOf(
            "title" to "JavaScript",
            "desc" to "Bahasa utama untuk web"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoreBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupListView()
    }

    // Toolbar
    private fun setupToolbar() {

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "More Fragment"
    }

    // ListView
    private fun setupListView() {

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(
                android.R.id.text1,
                android.R.id.text2
            )
        )

        // Hubungkan adapter ke ListView
        binding.listViewItems.adapter = adapter

        // Klik item ListView
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->

            val selectedItem = dataListWithDesc[position]

            val title = selectedItem["title"]
            val desc = selectedItem["desc"]

            Toast.makeText(
                requireContext(),
                "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}