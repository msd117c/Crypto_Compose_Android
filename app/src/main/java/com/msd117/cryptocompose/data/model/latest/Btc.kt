package com.msd117.cryptocompose.data.model.latest

import com.squareup.moshi.Json

data class Btc(
    val price: Int? = null,
    @Json(name = "volume_24h")
    val volume24h: Int? = null,
    @Json(name = "percent_change_1h")
    val percentChange1h: Int? = null,
    @Json(name = "percent_change_24h")
    val percentChange24h: Int? = null,
    @Json(name = "percent_change_7d")
    val percentChange7d: Int? = null,
    @Json(name = "market_cap")
    val marketCap: Int? = null,
    @Json(name = "last_updated")
    val lastUpdated: String? = null
)