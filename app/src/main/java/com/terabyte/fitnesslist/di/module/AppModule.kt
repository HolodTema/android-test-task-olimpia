package com.terabyte.fitnesslist.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.terabyte.fitnesslist.BASE_URL
import com.terabyte.fitnesslist.RETROFIT_CONNECT_TIMEOUT
import com.terabyte.fitnesslist.RETROFIT_READ_TIMEOUT
import com.terabyte.fitnesslist.di.component.ActivityComponent
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    subcomponents = [ActivityComponent::class]
)
class AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(RETROFIT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}