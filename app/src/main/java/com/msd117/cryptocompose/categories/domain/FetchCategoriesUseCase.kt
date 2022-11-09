package com.msd117.cryptocompose.categories.domain

import com.msd117.cryptocompose.categories.data.CategoriesRepository
import com.msd117.cryptocompose.categories.domain.mapper.toDomain
import com.msd117.cryptocompose.categories.domain.model.CategoryDomain
import javax.inject.Inject

class FetchCategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke(start: Int, limit: Int): List<CategoryDomain> {
        return categoriesRepository.fetchCategories(start, limit).data.toDomain()
    }
}
