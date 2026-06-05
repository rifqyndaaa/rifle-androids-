package com.example.myapplication.Message

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Message.tutorial.TutorialMessageActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://api.dicebear.com/7.x/avataaars/png?seed=Alya"),
        MessageModel("Budi", "Sudah makan?", "https://api.dicebear.com/7.x/avataaars/png?seed=Budi"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://api.dicebear.com/7.x/avataaars/png?seed=Citra"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://api.dicebear.com/7.x/avataaars/png?seed=Dika"),
        MessageModel("Eka", "Nice job kemarin!", "https://api.dicebear.com/7.x/avataaars/png?seed=Eka"),
        MessageModel("Fajar", "Lagi ngapain?", "https://api.dicebear.com/7.x/avataaars/png?seed=Fajar"),
        MessageModel("Gita", "Boleh minta tolong?", "https://api.dicebear.com/7.x/avataaars/png?seed=Gita"),
        MessageModel("Hana", "Lihat email ya", "https://api.dicebear.com/7.x/avataaars/png?seed=Hana"),
        MessageModel("Irfan", "Oke noted", "https://api.dicebear.com/7.x/avataaars/png?seed=Irfan"),
        MessageModel("Joko", "Sampai jumpa besok", "https://api.dicebear.com/7.x/avataaars/png?seed=Joko")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "Message Fragment"
        }

        setHasOptionsMenu(true)

        // Setup Adapter
        binding.listMessageItems.adapter = MessageAdapter(
            requireContext(),
            messageList
        )
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(
            R.menu.message_toolbar_menu,
            menu
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_tutorial -> {
                startActivity(
                    Intent(
                        requireContext(),
                        TutorialMessageActivity::class.java
                    )
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}