package com.msd117.cryptocompose.data.model.info

import com.squareup.moshi.Json

data class Status(
    val timestamp: String,
    @Json(name = "error_code") val errorCode: Int,
    @Json(name = "error_message") val errorMessage: String?,
    val elapsed: Int,
    @Json(name = "credit_count") val creditCount: Int
)
