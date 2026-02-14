package com.terabyte.fitnesslist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.terabyte.fitnesslist.application.MyApplication
import com.terabyte.fitnesslist.databinding.FragmentLessonsBinding
import com.terabyte.fitnesslist.ui.LessonAdapter
import com.terabyte.fitnesslist.viewmodel.MainViewModel

class LessonsFragment: Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private lateinit var binding: FragmentLessonsBinding

    private lateinit var lessonAdapter: LessonAdapter

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApplication).appComponent
            .inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonsBinding.inflate(layoutInflater)

        lessonAdapter = LessonAdapter(inflater)
        binding.recyclerLessons.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lessonAdapter
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveDataLessonListItems.observe(viewLifecycleOwner) { lessons ->
            lessonAdapter.submitList(lessons)
        }
    }

    companion object {
        fun newInstance(): LessonsFragment {
            return LessonsFragment()
        }
    }
}