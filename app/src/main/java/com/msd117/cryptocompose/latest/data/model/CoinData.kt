package com.msd117.cryptocompose.latest.data.model

import com.squareup.moshi.Json

data class CoinData(
    val id: Int?,
    val name: String?,
    val symbol: String?,
    val slug: String?,
    @Json(name = "cmc_rank")
    val cmcRank: Int?,
    @Json(name = "num_market_pairs")
    val numMarketPairs: Int?,
    @Json(name = "circulating_supply")
    val circulatingSupply: Double?,
    @Json(name = "total_supply")
    val totalSupply: Double?,
    @Json(name = "max_supply")
    val maxSupply: Double?,
    @Json(name = "last_updated")
    val lastUpdated: String?,
    @Json(name = "date_added")
    val dateAdded: String?,
    val tags: List<String>?,
    val platform: Any?,
    val quote: Quote?
)
