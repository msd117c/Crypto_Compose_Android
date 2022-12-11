package com.msd.domain.categories

import com.msd.domain.categories.model.CategoryDomain

interface ICategoriesRepository {
    suspend fun fetchCategories(start: Int, limit: Int): List<CategoryDomain>
}