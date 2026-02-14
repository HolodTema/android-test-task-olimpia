package com.terabyte.fitnesslist.di.module

import androidx.lifecycle.ViewModel
import com.terabyte.fitnesslist.di.component.FragmentComponent
import com.terabyte.fitnesslist.di.mapkey.ViewModelKey
import com.terabyte.fitnesslist.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [FragmentComponent::class]
)
interface ActivityModule {

    //add MainViewModel to multibinding map collection
    //this map collection we use in ViewModelFactory
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel

}