package com.terabyte.fitnesslist.activity

import android.os.Build
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.terabyte.fitnesslist.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.P]
)
class MainActivityTest {
    private lateinit var activity: MainActivity
    private lateinit var activityController: ActivityController<MainActivity>

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activity = activityController
            .create()
            .start()
            .resume()
            .get()
    }

    @Test
    fun bottomNavView_selectedItemAfterRecreate() {
        val bottomNavView = activity.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNavView.selectedItemId = R.id.menu_item_chat

        assertEquals(bottomNavView.selectedItemId, R.id.menu_item_chat)

        activityController.recreate()

        val recreatedActivity = activityController.get()
        val recreatedBottomNavView = recreatedActivity.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        assertEquals(recreatedBottomNavView.selectedItemId, R.id.menu_item_chat)
    }

    @After
    fun tearDown() {
        activityController.destroy()
    }

}