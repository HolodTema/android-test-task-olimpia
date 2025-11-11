package com.terabyte.fitnesslist.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.terabyte.fitnesslist.R
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @Test
    fun bottomNavView_selectedItemAfterRecreate() {
        onView(withId(R.id.menu_item_chat))
            .perform(click())

        onView(withId(R.id.menu_item_chat))
            .check(matches(isSelected()))

        scenario.recreate()

        onView(withId(R.id.menu_item_chat))
            .check(matches(isSelected()))
    }

    @After
    fun tearDown() {
        scenario.close()
    }

}