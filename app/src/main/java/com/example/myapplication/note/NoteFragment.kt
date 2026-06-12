package com.example.myapplication.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentNoteBinding
import com.example.myapplication.note.NoteFormActivity

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔥 INI YANG BIKIN PINDAH KE FORM
        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(requireContext(), NoteFormActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Catatan"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}