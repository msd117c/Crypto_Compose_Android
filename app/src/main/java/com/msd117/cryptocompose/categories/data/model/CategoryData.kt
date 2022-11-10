package com.msd117.cryptocompose.categories.data.model

import com.squareup.moshi.Json

data class CategoryData(
    val id: String?,
    val name: String?,
    val title: String?,
    val description: String?,
    @Json(name = "num_tokens")
    val numTokens: Int?,
    @Json(name = "avg_price_change")
    val avgPriceChange: Double?,
    @Json(name = "market_cap")
    val marketCap: Double?,
    @Json(name = "market_cap_change")
    val marketCapChange: Double?,
    val volume: Double?,
    @Json(name = "volume_change")
    val volumeChange: Double?,
    @Json(name = "last_updated")
    val lastUpdated: String?
)
