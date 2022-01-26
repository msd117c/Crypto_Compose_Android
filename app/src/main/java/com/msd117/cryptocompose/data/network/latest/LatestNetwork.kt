package com.msd117.cryptocompose.data.network.latest

import com.msd117.cryptocompose.data.model.latest.LatestResponse
import com.msd117.cryptocompose.utils.ApiConstants.latestEndpoint
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestNetwork {

    @GET(latestEndpoint)
    suspend fun fetchLatest(
        @Query("start") start: Int,
        @Query("limit") limit: Int,
        @Query("sort") sort: String
    ): LatestResponse
}