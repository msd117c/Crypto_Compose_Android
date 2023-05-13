package com.msd.data.categories

import com.msd.data.categories.mapper.CategoryDomainMapper.toDomain
import com.msd.data.categories.network.CategoriesRemote
import com.msd.domain.categories.ICategoriesRepository
import com.msd.domain.categories.model.CategoryDomain
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesRemote: CategoriesRemote
) : ICategoriesRepository {

    override suspend fun fetchCategories(start: Int, limit: Int): List<CategoryDomain> {
        return categoriesRemote.fetchRemoteCategories(start, limit).data
            .mapNotNull { it.toDomain() }
    }
}
