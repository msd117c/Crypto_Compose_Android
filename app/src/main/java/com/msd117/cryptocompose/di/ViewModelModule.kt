package com.msd117.cryptocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    fun provideCoroutineScope(): CoroutineScope? = null
}