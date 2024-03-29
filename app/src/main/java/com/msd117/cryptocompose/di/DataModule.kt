package com.msd117.cryptocompose.di

import com.msd.core.network_capabilities.NetworkCapabilitiesProvider
import com.msd.data.categories.CategoriesRepository
import com.msd.data.coin_details.CoinDetailRepository
import com.msd.data.latest_coins.LatestCoinsRepository
import com.msd.domain.categories.ICategoriesRepository
import com.msd.domain.coin_details.ICoinDetailsRepository
import com.msd.domain.latest_coins_list.ILatestCoinsRepository
import com.msd.domain.network_capabilities.INetworkCapabilitiesProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLatestCoinsRepository(latestCoinsRepository: LatestCoinsRepository): ILatestCoinsRepository

    @Binds
    abstract fun bindCoinDetailsRepository(coinDetailRepository: CoinDetailRepository): ICoinDetailsRepository

    @Binds
    abstract fun bindCategoriesRepository(categoriesRepository: CategoriesRepository): ICategoriesRepository

    @Binds
    abstract fun bindNetworkCapabilities(networkCapabilitiesProvider: NetworkCapabilitiesProvider): INetworkCapabilitiesProvider
}