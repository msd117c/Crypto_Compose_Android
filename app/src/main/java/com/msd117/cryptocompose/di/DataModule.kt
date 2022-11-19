package com.msd117.cryptocompose.di

import com.msd.coin_details.CoinDetailRepository
import com.msd.coin_details.ICoinDetailsRepository
import com.msd.core.network_capabilities.NetworkCapabilitiesProvider
import com.msd.domain.network_capabilities.INetworkCapabilitiesProvider
import com.msd.latest_coins.LatestCoinsRepository
import com.msd.latest_coins_list.ILatestCoinsRepository
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
    abstract fun bindNetworkCapabilities(networkCapabilitiesProvider: NetworkCapabilitiesProvider): INetworkCapabilitiesProvider
}