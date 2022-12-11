package com.msd.data.latest_coins.model

import com.squareup.moshi.Json

data class Usd(
    val price: Double?,
    @Json(name = "percent_change_1h")
    val percentChange1h: Double?
)
