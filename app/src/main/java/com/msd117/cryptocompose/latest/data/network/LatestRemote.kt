package com.msd117.cryptocompose.latest.data.network

import retrofit2.Retrofit
import javax.inject.Inject

class LatestRemote @Inject constructor(retrofitClient: Retrofit) {

    private val latestNetwork = retrofitClient.create(LatestNetwork::class.java)

    suspend fun fetchRemoteLatest(start: Int, limit: Int, sort: String) =
        latestNetwork.fetchLatest(start = start, limit = limit, sort = sort)
}
