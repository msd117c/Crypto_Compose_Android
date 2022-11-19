package com.msd.latest_coins.model

import com.squareup.moshi.Json

data class Quote(
    @Json(name = "USD")
    val usd: Usd?
)
