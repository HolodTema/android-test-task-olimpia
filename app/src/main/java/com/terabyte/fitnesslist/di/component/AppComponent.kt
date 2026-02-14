package com.terabyte.fitnesslist.di.component

import android.app.Application
import com.terabyte.fitnesslist.activity.MainActivity
import com.terabyte.fitnesslist.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}