package com.msd117.cryptocompose.di

import com.msd.data.categories.network.CategoriesNetwork
import com.msd.data.coin_details.model.CoinDetail
import com.msd.data.coin_details.network.CoinDetailNetwork
import com.msd.data.coin_details.network.CoinDetailRemote
import com.msd.data.latest_coins.network.LatestCoinsNetwork
import com.msd.network.NetworkProvider
import com.msd117.cryptocompose.BuildConfig
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
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

    @Provides
    fun provideLatestCoinsNetwork(retrofitClient: Retrofit): LatestCoinsNetwork {
        return retrofitClient.create(LatestCoinsNetwork::class.java)
    }

    @Provides
    fun provideCoinDetailNetwork(retrofitClient: Retrofit): CoinDetailNetwork {
        return retrofitClient.create(CoinDetailNetwork::class.java)
    }

    @Provides
    fun provideCoinDetailRemote(coinDetailNetwork: CoinDetailNetwork): CoinDetailRemote {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<CoinDetail> = moshi.adapter(CoinDetail::class.java)
        return CoinDetailRemote(coinDetailNetwork, jsonAdapter)
    }

    @Provides
    fun provideCategoriesNetwork(retrofitClient: Retrofit): CategoriesNetwork {
        return retrofitClient.create(CategoriesNetwork::class.java)
    }
}
