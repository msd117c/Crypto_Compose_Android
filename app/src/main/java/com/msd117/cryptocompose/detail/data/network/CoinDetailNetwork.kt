package com.msd117.cryptocompose.detail.data.network

import com.msd117.cryptocompose.utils.ApiConstants
import com.msd117.cryptocompose.utils.ApiConstants.COIN_DETAIL_SYMBOL_PARAMETER
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinDetailNetwork {

    @GET(ApiConstants.COIN_DETAIL_ENDPOINT)
    suspend fun fetchCoinDetailRemote(@Query(COIN_DETAIL_SYMBOL_PARAMETER) symbol: String): ResponseBody
}
