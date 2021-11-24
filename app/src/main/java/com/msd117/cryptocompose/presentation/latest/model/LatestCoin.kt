package com.msd117.cryptocompose.presentation.latest.model

data class LatestCoin(
    val id: Int? = null,
    val name: String,
    val symbol: String,
    val summary: String,
    val growth: Growth,
    val price: String,
    val icon: String,
    val slug: String? = null,
    val cmcRank: Int? = null,
    val numMarketPairs: Int? = null,
    val circulatingSupply: Double? = null,
    val totalSupply: Double? = null,
    val maxSupply: Double? = null,
    val lastUpdated: String? = null,
    val dateAdded: String? = null,
    val tags: List<String>? = null,
    val platform: Any? = null,
    val btc: Btc? = null,
    val eth: Eth? = null,
    val usd: Usd? = null
)

enum class Growth {
    POSITIVE,
    NEGATIVE,
    NONE
}

data class Btc(
    val price: Double? = null,
    val volume24h: Double? = null,
    val percentChange1h: Double? = null,
    val percentChange24h: Double? = null,
    val percentChange7d: Double? = null,
    val marketCap: Double? = null,
    val lastUpdated: String? = null
)

data class Eth(
    val price: Double? = null,
    val volume24h: Double? = null,
    val percentChange1h: Double? = null,
    val percentChange24h: Double? = null,
    val percentChange7d: Double? = null,
    val marketCap: Double? = null,
    val lastUpdated: String? = null
)

data class Usd(
    val price: Double? = null,
    val volume24h: Double? = null,
    val percentChange1h: Double? = null,
    val percentChange24h: Double? = null,
    val percentChange7d: Double? = null,
    val marketCap: Double? = null,
    val lastUpdated: String? = null,
    val isPositive: Boolean
)
