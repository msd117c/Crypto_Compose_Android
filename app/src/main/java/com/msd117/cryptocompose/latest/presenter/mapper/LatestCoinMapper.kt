package com.msd117.cryptocompose.latest.presenter.mapper

import com.msd117.cryptocompose.domain.model.latest.*
import com.msd117.cryptocompose.latest.presenter.model.*

fun List<LatestCoinDomain>.toPresentation(): List<LatestCoin> {
    return map { latestCoinDomain ->
        with(latestCoinDomain) {
            LatestCoin(
                id = id,
                name = name,
                symbol = symbol,
                summary = summary,
                growth = growth.toPresentation(),
                price = price,
                icon = icon,
                slug = slug,
                cmcRank = cmcRank,
                numMarketPairs = numMarketPairs,
                circulatingSupply = circulatingSupply,
                totalSupply = totalSupply,
                maxSupply = maxSupply,
                lastUpdated = lastUpdated,
                dateAdded = dateAdded,
                tags = tags,
                platform = platform,
                btc = btc?.toPresentation(),
                eth = eth?.toPresentation(),
                usd = usd?.toPresentation()
            )
        }
    }
}

private fun GrowthDomain.toPresentation() = when (this) {
    GrowthDomain.POSITIVE -> Growth.POSITIVE
    GrowthDomain.NEGATIVE -> Growth.NEGATIVE
    GrowthDomain.NONE -> Growth.NONE
}

private fun BtcDomain.toPresentation() = Btc(
    price = price,
    volume24h = volume24h,
    percentChange1h = percentChange1h,
    percentChange24h = percentChange24h,
    percentChange7d = percentChange7d,
    marketCap = marketCap,
    lastUpdated = lastUpdated
)

private fun EthDomain.toPresentation() = Eth(
    price = price,
    volume24h = volume24h,
    percentChange1h = percentChange1h,
    percentChange24h = percentChange24h,
    percentChange7d = percentChange7d,
    marketCap = marketCap,
    lastUpdated = lastUpdated
)

private fun UsdDomain.toPresentation() = Usd(
    price = price,
    volume24h = volume24h,
    percentChange1h = percentChange1h,
    percentChange24h = percentChange24h,
    percentChange7d = percentChange7d,
    marketCap = marketCap,
    lastUpdated = lastUpdated,
    isPositive = (percentChange1h ?: 0.0) > (percentChange24h ?: 0.0)
)
