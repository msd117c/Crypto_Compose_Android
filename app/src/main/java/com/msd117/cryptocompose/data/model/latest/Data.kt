package com.msd117.cryptocompose.data.model.latest

import com.squareup.moshi.Json

data class Data(
    val id: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val slug: String? = null,
    @Json(name = "cmc_rank")
    val cmcRank: Int? = null,
    @Json(name = "num_market_pairs")
    val numMarketPairs: Int? = null,
    @Json(name = "circulating_supply")
    val circulatingSupply: Int? = null,
    @Json(name = "total_supply")
    val totalSupply: Int? = null,
    @Json(name = "max_supply")
    val maxSupply: Int? = null,
    @Json(name = "last_updated")
    val lastUpdated: String? = null,
    @Json(name = "date_added")
    val dateAdded: String? = null,
    val tags: List<String>? = null,
    val platform: Any? = null,
    val quote: Quote? = null
)