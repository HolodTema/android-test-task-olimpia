package com.terabyte.fitnesslist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.terabyte.fitnesslist.R
import com.terabyte.fitnesslist.application.MyApplication
import com.terabyte.fitnesslist.databinding.ActivityMainBinding
import com.terabyte.fitnesslist.di.component.ActivityComponent
import com.terabyte.fitnesslist.fragment.LessonsFragment
import com.terabyte.fitnesslist.fragment.NotesFragment
import com.terabyte.fitnesslist.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (application as MyApplication).appComponent
        activityComponent = appComponent.activityComponentFactory().create()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavMain.selectedItemId = viewModel.liveDataBottomNavMenuId.value!!
        setFragment(viewModel.liveDataBottomNavMenuId.value!!)
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNavMain.setOnItemSelectedListener { menuItem ->
            viewModel.liveDataBottomNavMenuId.value = menuItem.itemId
            true
        }

        viewModel.liveDataBottomNavMenuId.observe(this) { menuItemId ->
            setFragment(menuItemId)
        }
    }

    private fun setFragment(menuItemId: Int) {
        val fragment = when(menuItemId) {
            R.id.menu_item_lessons -> {
                LessonsFragment.newInstance()
            }
            R.id.menu_item_notes -> {
                NotesFragment.newInstance()
            }
            R.id.menu_item_add -> {
                NotesFragment.newInstance()
            }
            R.id.menu_item_chat -> {
                NotesFragment.newInstance()
            }
            R.id.menu_item_more -> {
                NotesFragment.newInstance()
            }
            else -> {
                LessonsFragment.newInstance()
            }
        }
        if (supportFragmentManager.findFragmentById(R.id.frame_main_fragment) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_main_fragment, fragment)
                .commit()
        }
        else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_main_fragment, fragment)
                .commit()
        }
    }
}