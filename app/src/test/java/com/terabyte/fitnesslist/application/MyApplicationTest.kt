package com.terabyte.fitnesslist.application

import android.content.Context
import com.terabyte.fitnesslist.retrofit.RetrofitHelper
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic

class MyApplicationTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockMyApplication: MyApplication

    private lateinit var mockedStaticRetrofitHelper: MockedStatic<RetrofitHelper>

    @Before
    fun setUp() {
        mockContext = mock()
        mockMyApplication = mock()
        mockedStaticRetrofitHelper = mockStatic(RetrofitHelper::class.java)
        Mockito.reset(mockMyApplication, mockContext)

        mockedStaticRetrofitHelper.reset()
    }

//    @Test
//    fun onCreate_notInitRetrofitHelper_throwsException() {
//        //arrange
//        Mockito.`when`(mockMyApplication.onCreate()).then {
//            //do nothing
//        }
//
//        //act
//        mockMyApplication.onCreate()
//
//        //assert
//        try {
//            Mockito.
//            fail()
//        }
//        catch (e: UninitializedPropertyAccessException) {
//            //do nothing
//        }
//    }

    @Test
    fun onCreate_initRetrofitHelper() {
        //arrange
        Mockito.`when`(mockMyApplication.onCreate()).then {
            RetrofitHelper.init(mockContext)
        }

        //act
        mockMyApplication.onCreate()

        //assert
        try {
            RetrofitHelper.get()
        }
        catch (e: UninitializedPropertyAccessException) {
            fail()
        }
    }

    @After
    fun tearDown() {
        mockedStaticRetrofitHelper.close()
    }
}