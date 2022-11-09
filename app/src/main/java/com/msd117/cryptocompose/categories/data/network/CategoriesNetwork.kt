package com.msd117.cryptocompose.categories.data.network

import com.msd117.cryptocompose.categories.data.model.CategoriesResponse
import com.msd117.cryptocompose.utils.ApiConstants
import com.msd117.cryptocompose.utils.ApiConstants.QUERY_LIMIT_PARAMETER
import com.msd117.cryptocompose.utils.ApiConstants.QUERY_START_PARAMETER
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesNetwork {

    @GET(ApiConstants.CATEGORIES_ENDPOINT)
    suspend fun fetchCategories(
        @Query(QUERY_START_PARAMETER) start: Int,
        @Query(QUERY_LIMIT_PARAMETER) limit: Int
    ): CategoriesResponse
}
