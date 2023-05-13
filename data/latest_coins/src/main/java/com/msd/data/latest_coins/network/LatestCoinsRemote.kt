package com.msd.data.latest_coins.network

import javax.inject.Inject

class LatestCoinsRemote @Inject constructor(private val network: LatestCoinsNetwork) {

    suspend fun fetchRemoteLatest(start: Int, limit: Int, sort: String) =
        network.fetchLatestCoins(start = start, limit = limit, sort = sort)
}
