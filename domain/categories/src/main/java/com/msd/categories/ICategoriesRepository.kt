package com.msd.categories

import com.msd.categories.model.CategoryDomain

interface ICategoriesRepository {
    suspend fun fetchCategories(start: Int, limit: Int): List<CategoryDomain>
}