package com.terabyte.fitnesslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

//factory can be used in many viewModels, and it does not depend on actions inside application
//so, we can make it Singleton.
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = creators[modelClass]
        if (provider == null) {
            throw IllegalArgumentException("Invalid ViewModel type.")
        }
        return provider.get() as T
    }
}