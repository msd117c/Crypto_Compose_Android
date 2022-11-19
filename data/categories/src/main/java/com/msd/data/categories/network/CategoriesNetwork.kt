package com.msd.data.categories.network

import com.msd.data.categories.model.CategoriesResponse
import com.msd.network.ApiConstants.CATEGORIES_ENDPOINT
import com.msd.network.ApiConstants.QUERY_LIMIT_PARAMETER
import com.msd.network.ApiConstants.QUERY_START_PARAMETER
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesNetwork {

    @GET(CATEGORIES_ENDPOINT)
    suspend fun fetchCategories(
        @Query(QUERY_START_PARAMETER) start: Int,
        @Query(QUERY_LIMIT_PARAMETER) limit: Int
    ): CategoriesResponse
}
