package com.msd.data.latest_coins.network

import retrofit2.Retrofit
import javax.inject.Inject

class LatestCoinsRemote @Inject constructor(retrofitClient: Retrofit) {

    private val latestNetwork = retrofitClient.create(LatestCoinsNetwork::class.java)

    suspend fun fetchRemoteLatest(start: Int, limit: Int, sort: String) =
        latestNetwork.fetchLatestCoins(start = start, limit = limit, sort = sort)
}
