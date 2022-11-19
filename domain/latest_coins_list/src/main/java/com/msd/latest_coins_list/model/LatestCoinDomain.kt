package com.msd.latest_coins_list.model

data class LatestCoinDomain(
    val id: Int,
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
