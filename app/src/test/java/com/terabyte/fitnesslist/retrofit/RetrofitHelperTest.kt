package com.terabyte.fitnesslist.retrofit

import android.content.Context
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RetrofitHelperTest {

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setUp() {
        mockContext = mock<Context>()
    }

    @Test
    fun get_instanceInit_returnsInstance() {
        RetrofitHelper.init(mockContext)

        try {
            val helper = RetrofitHelper.get()
        }
        catch(e: UninitializedPropertyAccessException) {
            fail()
        }
    }

    @Test
    fun get_noInitCall_throwsException() {
        try {
            val helper = RetrofitHelper.get()
            fail()
        } catch (e: UninitializedPropertyAccessException) {

        }
    }
}