package com.terabyte.fitnesslist.di.component

import com.terabyte.fitnesslist.di.module.FragmentModule
import com.terabyte.fitnesslist.di.scope.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }
}