package com.terabyte.fitnesslist.retrofit

import android.util.Log
import com.terabyte.fitnesslist.LOG_TAG_ERROR
import com.terabyte.fitnesslist.json.LessonsInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitHelper @Inject constructor(private val retrofit: Retrofit) {

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
}