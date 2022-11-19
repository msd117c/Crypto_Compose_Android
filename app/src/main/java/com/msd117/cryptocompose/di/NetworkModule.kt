package com.msd117.cryptocompose.di

import com.msd.network.NetworkProvider
import com.msd117.cryptocompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofitClient(): Retrofit {
        return NetworkProvider.buildRetrofitClient(BuildConfig.API_KEY)
    }
}
