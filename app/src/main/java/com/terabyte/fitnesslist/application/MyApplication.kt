package com.terabyte.fitnesslist.application

import android.app.Application
import com.terabyte.fitnesslist.di.component.AppComponent
import com.terabyte.fitnesslist.di.component.DaggerAppComponent
import com.terabyte.fitnesslist.retrofit.RetrofitHelper

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this)
    }

}