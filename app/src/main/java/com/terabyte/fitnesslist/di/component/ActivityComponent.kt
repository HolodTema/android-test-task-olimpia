package com.terabyte.fitnesslist.di.component

import com.terabyte.fitnesslist.activity.MainActivity
import com.terabyte.fitnesslist.di.module.ActivityModule
import com.terabyte.fitnesslist.di.scope.ActivityScope
import dagger.Subcomponent


@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}