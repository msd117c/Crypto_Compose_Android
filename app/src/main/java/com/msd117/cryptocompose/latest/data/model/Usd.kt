package com.msd117.cryptocompose.latest.data.model

import com.squareup.moshi.Json

data class Usd(
    val price: Double?,
    @Json(name = "percent_change_1h")
    val percentChange1h: Double?
)
