package com.msd117.cryptocompose.latest.domain.model

data class LatestCoinDomain(
    val name: String,
    val symbol: String,
    val summary: String,
    val growth: GrowthDomain,
    val price: String,
    val icon: String,
    val cmcRank: Int
)

enum class GrowthDomain {
    POSITIVE,
    NEGATIVE,
    NONE
}
