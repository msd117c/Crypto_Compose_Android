package com.msd117.cryptocompose.categories.data.model

data class CategoriesResponse(
    val data: List<CategoryData>,
    val status: CategoriesStatus?
)
