package com.msd.domain.categories

import com.msd.domain.categories.model.CategoryDomain
import javax.inject.Inject

class FetchCategoriesUseCase @Inject constructor(
    private val categoriesRepository: ICategoriesRepository
) {

    suspend operator fun invoke(start: Int, limit: Int): List<CategoryDomain> {
        return categoriesRepository.fetchCategories(start, limit)
    }
}
