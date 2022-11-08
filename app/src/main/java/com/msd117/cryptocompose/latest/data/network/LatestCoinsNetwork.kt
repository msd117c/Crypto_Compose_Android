package com.msd117.cryptocompose.latest.data.network

import com.msd117.cryptocompose.latest.data.model.LatestCoinsResponse
import com.msd117.cryptocompose.utils.ApiConstants.LATEST_COINS_ENDPOINT
import com.msd117.cryptocompose.utils.ApiConstants.LATEST_COINS_LIMIT_PARAMETER
import com.msd117.cryptocompose.utils.ApiConstants.LATEST_COINS_SORT_PARAMETER
import com.msd117.cryptocompose.utils.ApiConstants.LATEST_COINS_START_PARAMETER
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestCoinsNetwork {

    @GET(LATEST_COINS_ENDPOINT)
    suspend fun fetchLatestCoins(
        @Query(LATEST_COINS_START_PARAMETER) start: Int,
        @Query(LATEST_COINS_LIMIT_PARAMETER) limit: Int,
        @Query(LATEST_COINS_SORT_PARAMETER) sort: String
    ): LatestCoinsResponse
}
