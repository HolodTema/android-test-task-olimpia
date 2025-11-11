package com.terabyte.fitnesslist.ui

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date


class LessonListItemTest {

    @Test
    fun compareTo_nullDateToNullDate_returnsInt() {
        //arrange
        val lesson1 = LessonListItem(
            id = "0",
            "lesson1",
            "10:00",
            "12:00",
            "12:00",
            null,
            "somePlace",
            "#ffffff",
            "trainer1"
        )

        val lesson2 = LessonListItem(
            id = "1",
            "lesson2",
            "16:00",
            "19:00",
            "19:00",
            null,
            "somePlace",
            "#ffffff",
            "trainer2"
        )

        //act
        val compareResult = lesson1.compareTo(lesson2)

        //assert
        assertEquals(-1, compareResult)
    }

    @Test
    fun compareTo_nullDateToNonNullDate_returnsInt() {
        //arrange
        val lesson1 = LessonListItem(
            id = "0",
            "lesson1",
            "10:00",
            "12:00",
            "12:00",
            null,
            "somePlace",
            "#ffffff",
            "trainer1"
        )

        val lesson2 = LessonListItem(
            id = "1",
            "lesson2",
            "16:00",
            "19:00",
            "19:00",
            Date(),
            "somePlace",
            "#ffffff",
            "trainer2"
        )

        //act
        val compareResult = lesson1.compareTo(lesson2)

        //assert
        assertEquals(-1, compareResult)
    }

    @Test
    fun compareTo_nonNullDateToNullDate_returnsInt() {
        //arrange
        val lesson1 = LessonListItem(
            id = "0",
            "lesson1",
            "10:00",
            "12:00",
            "12:00",
            Date(),
            "somePlace",
            "#ffffff",
            "trainer1"
        )

        val lesson2 = LessonListItem(
            id = "1",
            "lesson2",
            "16:00",
            "19:00",
            "19:00",
            null,
            "somePlace",
            "#ffffff",
            "trainer2"
        )

        //act
        val compareResult = lesson1.compareTo(lesson2)

        //assert
        assertEquals(1, compareResult)
    }

    @Test
    fun compareTo_equals_returnsInt() {
        //arrange
        val lesson1 = LessonListItem(
            id = "0",
            "lesson1",
            "10:00",
            "12:00",
            "12:00",
            Date(),
            "somePlace",
            "#ffffff",
            "trainer1"
        )
        val lesson2 = lesson1

        //act
        val compareResult = lesson1.compareTo(lesson2)

        //assert
        assertEquals(0, compareResult)
    }

}