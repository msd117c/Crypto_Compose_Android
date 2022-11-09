package com.msd117.cryptocompose.categories.data.model

data class CategoriesResponse(
    val data: ArrayList<CategoryData>,
    val status: CategoriesStatus?
)
