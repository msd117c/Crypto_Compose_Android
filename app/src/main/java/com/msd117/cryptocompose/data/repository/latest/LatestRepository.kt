package com.msd117.cryptocompose.data.repository.latest

import com.msd117.cryptocompose.data.network.latest.LatestNetwork
import retrofit2.Retrofit
import javax.inject.Inject

class LatestRepository @Inject constructor(retrofitClient: Retrofit) {

    private val latestNetwork = retrofitClient.create(LatestNetwork::class.java)

    suspend fun fetchLatest() = latestNetwork.fetchLatest()
}