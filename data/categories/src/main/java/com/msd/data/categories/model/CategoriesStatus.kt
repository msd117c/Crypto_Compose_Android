package com.msd.data.categories.model

import com.squareup.moshi.Json

data class CategoriesStatus(
    val timestamp: String?,
    @Json(name = "error_code")
    val errorCode: Int?,
    @Json(name = "error_message")
    val errorMessage: String?,
    val elapsed: Int?,
    @Json(name = "credit_count")
    val creditCount: Int?
)
