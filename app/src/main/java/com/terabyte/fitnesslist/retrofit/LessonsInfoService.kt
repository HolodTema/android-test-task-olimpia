package com.terabyte.fitnesslist.retrofit

import com.terabyte.fitnesslist.json.LessonsInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LessonsInfoService {

    @GET("schedule/get_v3/")
    fun getLessonsInfo(@Query("club_id") clubId: Int): Call<LessonsInfo>

}