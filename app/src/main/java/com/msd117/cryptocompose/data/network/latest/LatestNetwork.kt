package com.msd117.cryptocompose.data.network.latest

import com.msd117.cryptocompose.data.model.latest.LatestResponse
import com.msd117.cryptocompose.utils.ApiConstants.latestEndpoint
import retrofit2.http.GET

interface LatestNetwork {

    @GET(latestEndpoint)
    suspend fun fetchLatest(): LatestResponse
}