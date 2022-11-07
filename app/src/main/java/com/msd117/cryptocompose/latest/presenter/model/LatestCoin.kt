package com.msd117.cryptocompose.latest.presenter.model

data class LatestCoin(
    val id: Int?,
    val name: String,
    val symbol: String,
    val summary: String,
    val growth: Growth,
    val price: String,
    val icon: String,
    val slug: String?,
    val cmcRank: Int?,
    val numMarketPairs: Int?,
    val circulatingSupply: Double?,
    val totalSupply: Double?,
    val maxSupply: Double?,
    val lastUpdated: String?,
    val dateAdded: String?,
    val tags: List<String>?,
    val platform: Any?,
    val btc: Btc?,
    val eth: Eth?,
    val usd: Usd?
)

enum class Growth {
    POSITIVE,
    NEGATIVE,
    NONE
}

data class Btc(
    val price: Double?,
    val volume24h: Double?,
    val percentChange1h: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val lastUpdated: String?
)

data class Eth(
    val price: Double?,
    val volume24h: Double?,
    val percentChange1h: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val lastUpdated: String?
)

data class Usd(
    val price: Double?,
    val volume24h: Double?,
    val percentChange1h: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val lastUpdated: String?,
    val isPositive: Boolean
)
