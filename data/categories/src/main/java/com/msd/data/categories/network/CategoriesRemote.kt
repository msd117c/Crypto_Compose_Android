package com.msd.data.categories.network

import javax.inject.Inject

class CategoriesRemote @Inject constructor(private val network: CategoriesNetwork) {

    suspend fun fetchRemoteCategories(start: Int, limit: Int) =
        network.fetchCategories(start, limit)
}
