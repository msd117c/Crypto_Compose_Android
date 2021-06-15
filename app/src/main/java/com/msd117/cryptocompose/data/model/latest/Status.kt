package com.msd117.cryptocompose.data.model.latest

import com.squareup.moshi.Json

data class Status(
    val timestamp: String? = null,
    @Json(name = "error_code")
    val errorCode: Int? = null,
    @Json(name = "error_message")
    val errorMessage: String? = null,
    val elapsed: Int? = null,
    @Json(name = "credit_count")
    val creditCount: Int? = null
)