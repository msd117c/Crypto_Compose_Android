package com.msd.data.categories

import com.msd.categories.ICategoriesRepository
import com.msd.categories.model.CategoryDomain
import com.msd.data.categories.mapper.toDomain
import com.msd.data.categories.network.CategoriesRemote
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesRemote: CategoriesRemote
) : ICategoriesRepository {

    override suspend fun fetchCategories(start: Int, limit: Int): List<CategoryDomain> {
        return categoriesRemote.fetchRemoteCategories(start, limit).data.toDomain()
    }
}
