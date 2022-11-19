package com.msd.data.categories.model

data class CategoriesResponse(
    val data: List<CategoryData>,
    val status: CategoriesStatus?
)
