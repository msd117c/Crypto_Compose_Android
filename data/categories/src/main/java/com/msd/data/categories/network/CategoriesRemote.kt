package com.msd.data.categories.network

import retrofit2.Retrofit
import javax.inject.Inject

class CategoriesRemote @Inject constructor(retrofitClient: Retrofit) {

    private val categoriesNetwork = retrofitClient.create(CategoriesNetwork::class.java)

    suspend fun fetchRemoteCategories(start: Int, limit: Int) =
        categoriesNetwork.fetchCategories(start = start, limit = limit)
}
