package com.terabyte.fitnesslist.retrofit

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.terabyte.fitnesslist.BASE_URL
import com.terabyte.fitnesslist.LOG_TAG_ERROR
import com.terabyte.fitnesslist.RETROFIT_CONNECT_TIMEOUT
import com.terabyte.fitnesslist.RETROFIT_READ_TIMEOUT
import com.terabyte.fitnesslist.json.LessonsInfo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper private constructor(context: Context) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(RETROFIT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(RETROFIT_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun getLessonsInfo(clubId: Int, listener: (classesInfo: LessonsInfo?) -> Unit) {
        val service = retrofit.create(LessonsInfoService::class.java)
        val call = service.getLessonsInfo(clubId)
        call.enqueue(object : Callback<LessonsInfo> {
            override fun onResponse(
                call: Call<LessonsInfo?>,
                response: Response<LessonsInfo?>
            ) {
                listener(response.body())
            }

            override fun onFailure(
                call: Call<LessonsInfo?>,
                t: Throwable
            ) {
                Log.e(LOG_TAG_ERROR, "onFailure() in Retrofit request ${service.javaClass.name}", t)
                listener(null)
            }
        })
    }

    companion object {
        private lateinit var instance: RetrofitHelper

        fun get(): RetrofitHelper {
            return instance
        }

        fun init(context: Context) {
            instance = RetrofitHelper(context)
        }
    }
}