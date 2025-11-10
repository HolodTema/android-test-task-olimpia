package com.terabyte.fitnesslist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.terabyte.fitnesslist.databinding.FragmentNotesBinding

class NotesFragment: Fragment() {
    private lateinit var binding: FragmentNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): NotesFragment {
            return NotesFragment()
        }
    }
}