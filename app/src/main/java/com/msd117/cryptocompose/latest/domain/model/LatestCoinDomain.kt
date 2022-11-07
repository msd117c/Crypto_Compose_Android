package com.msd117.cryptocompose.domain.model.latest

data class LatestCoinDomain(
    val id: Int?,
    val name: String,
    val symbol: String,
    val summary: String,
    val growth: GrowthDomain,
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
    val btc: BtcDomain?,
    val eth: EthDomain?,
    val usd: UsdDomain?
)

enum class GrowthDomain {
    POSITIVE,
    NEGATIVE,
    NONE
}

data class BtcDomain(
    val price: Double?,
    val volume24h: Double?,
    val percentChange1h: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val lastUpdated: String?
)

data class EthDomain(
    val price: Double?,
    val volume24h: Double?,
    val percentChange1h: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val lastUpdated: String?
)

data class UsdDomain(
    val price: Double?,
    val volume24h: Double?,
    val percentChange1h: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val lastUpdated: String?,
    val isPositive: Boolean
)
