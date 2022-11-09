package com.msd117.cryptocompose.categories.data

import com.msd117.cryptocompose.categories.data.model.CategoriesResponse
import com.msd117.cryptocompose.categories.data.network.CategoriesRemote
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val categoriesRemote: CategoriesRemote) {

    suspend fun fetchCategories(start: Int, limit: Int): CategoriesResponse {
        return categoriesRemote.fetchRemoteCategories(start, limit)
    }
}
