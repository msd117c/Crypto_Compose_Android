package com.msd.latest_coins.network

import com.msd.latest_coins.model.LatestCoinsResponse
import com.msd.network.ApiConstants.LATEST_COINS_ENDPOINT
import com.msd.network.ApiConstants.LATEST_COINS_SORT_PARAMETER
import com.msd.network.ApiConstants.QUERY_LIMIT_PARAMETER
import com.msd.network.ApiConstants.QUERY_START_PARAMETER
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestCoinsNetwork {

    @GET(LATEST_COINS_ENDPOINT)
    suspend fun fetchLatestCoins(
        @Query(QUERY_START_PARAMETER) start: Int,
        @Query(QUERY_LIMIT_PARAMETER) limit: Int,
        @Query(LATEST_COINS_SORT_PARAMETER) sort: String
    ): LatestCoinsResponse
}
