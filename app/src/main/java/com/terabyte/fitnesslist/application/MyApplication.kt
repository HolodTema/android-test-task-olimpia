package com.terabyte.fitnesslist.application

import android.app.Application
import com.terabyte.fitnesslist.retrofit.RetrofitHelper

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitHelper.init(applicationContext)
    }

}