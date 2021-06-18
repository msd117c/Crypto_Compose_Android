package com.msd117.cryptocompose.data.model.latest

import com.squareup.moshi.Json

data class Eth(
    val price: Double? = null,
    @Json(name = "volume_24h")
    val volume24h: Double? = null,
    @Json(name = "percent_change_1h")
    val percentChange1h: Double? = null,
    @Json(name = "percent_change_24h")
    val percentChange24h: Double? = null,
    @Json(name = "percent_change_7d")
    val percentChange7d: Double? = null,
    @Json(name = "market_cap")
    val marketCap: Double? = null,
    @Json(name = "last_updated")
    val lastUpdated: String? = null
)