package com.msd117.cryptocompose.detail.data.network

import com.msd117.cryptocompose.utils.ApiConstants
import com.msd117.cryptocompose.utils.ApiConstants.QUERY_PARAMETER_SYMBOL
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface InfoNetwork {

    @GET(ApiConstants.infoEndpoint)
    suspend fun fetchInfo(@Query(QUERY_PARAMETER_SYMBOL) symbol: String): ResponseBody
}
