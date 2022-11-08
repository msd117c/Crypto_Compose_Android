package com.msd117.cryptocompose.latest.domain.mapper

import com.msd117.cryptocompose.latest.data.model.Btc
import com.msd117.cryptocompose.latest.data.model.CoinData
import com.msd117.cryptocompose.latest.data.model.Eth
import com.msd117.cryptocompose.latest.data.model.Usd
import com.msd117.cryptocompose.latest.domain.model.*
import com.msd117.cryptocompose.utils.ApiConstants.ICON_API_URL
import com.msd117.cryptocompose.utils.ApiConstants.ICON_API_URL_NAME
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun List<CoinData>.toDomain(): List<LatestCoinDomain> {
    return map { data ->
        with(data) {
            LatestCoinDomain(
                id = id,
                name = name ?: "",
                symbol = symbol ?: "",
                summary = toGrowthPercentage(),
                growth = toGrowth(),
                price = toFormattedPrice(),
                icon = toIconUrl(),
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
                btc = quote?.btc?.toBtcDomain(),
                eth = quote?.eth?.toEthDomain(),
                usd = quote?.usd?.toUsdDomain()
            )
        }
    }
}

private fun CoinData.toGrowthPercentage(): String {
    return quote?.usd?.percentChange1h?.let { percent ->
        val formattedPercent = BigDecimal(percent).setScale(2, RoundingMode.HALF_EVEN)
        if (formattedPercent > BigDecimal.ZERO) {
            "+$formattedPercent%"
        } else {
            "$formattedPercent%"
        }
    } ?: ""
}

private fun CoinData.toGrowth(): GrowthDomain {
    return when {
        (quote?.usd?.percentChange1h ?: 0.0) > 0.0 -> GrowthDomain.POSITIVE
        (quote?.usd?.percentChange1h ?: 0.0) < 0.0 -> GrowthDomain.NEGATIVE
        else -> GrowthDomain.NONE
    }
}

private fun CoinData.toFormattedPrice(): String {
    val price = quote?.usd?.price ?: 0.0
    val formattedPrice = StringBuilder()
    val formatter = Formatter(formattedPrice, Locale.US)
    formatter.format("$ %(,.2f", price)

    return formattedPrice.toString()
}

private fun CoinData.toIconUrl(): String {
    return (ICON_API_URL.replace(ICON_API_URL_NAME, name?.lowercase().orEmpty())).takeUnless {
        name.isNullOrEmpty()
    }.orEmpty()
}

private fun Btc.toBtcDomain() = BtcDomain(
    price = price,
    volume24h = volume24h,
    percentChange1h = percentChange1h,
    percentChange24h = percentChange24h,
    percentChange7d = percentChange7d,
    marketCap = marketCap,
    lastUpdated = lastUpdated
)

private fun Eth.toEthDomain() = EthDomain(
    price = price,
    volume24h = volume24h,
    percentChange1h = percentChange1h,
    percentChange24h = percentChange24h,
    percentChange7d = percentChange7d,
    marketCap = marketCap,
    lastUpdated = lastUpdated
)

private fun Usd.toUsdDomain() = UsdDomain(
    price = price,
    volume24h = volume24h,
    percentChange1h = percentChange1h,
    percentChange24h = percentChange24h,
    percentChange7d = percentChange7d,
    marketCap = marketCap,
    lastUpdated = lastUpdated,
    isPositive = (percentChange1h ?: 0.0) > (percentChange24h ?: 0.0)
)
