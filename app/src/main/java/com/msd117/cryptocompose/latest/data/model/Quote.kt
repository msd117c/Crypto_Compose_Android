package com.msd117.cryptocompose.latest.data.model

import com.squareup.moshi.Json

data class Quote(
    @Json(name = "USD")
    val usd: Usd?
)
