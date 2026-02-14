package com.terabyte.fitnesslist.di.component

import com.terabyte.fitnesslist.di.module.FragmentModule
import com.terabyte.fitnesslist.di.scope.FragmentScope
import com.terabyte.fitnesslist.fragment.LessonsFragment
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {

    fun inject(fragment: LessonsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }
}