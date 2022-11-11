package com.msd117.cryptocompose.latest.presenter.model

data class LatestCoin(
    val name: String,
    val symbol: String,
    val summary: String,
    val growth: Growth,
    val price: String,
    val icon: String,
    val cmcRank: Int
)

enum class Growth {
    POSITIVE,
    NEGATIVE,
    NONE
}
