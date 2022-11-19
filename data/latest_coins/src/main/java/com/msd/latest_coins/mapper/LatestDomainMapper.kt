package com.msd.latest_coins.mapper

import com.msd.latest_coins.model.CoinData
import com.msd.latest_coins_list.model.GrowthDomain
import com.msd.latest_coins_list.model.LatestCoinDomain
import com.msd.network.ApiConstants.ICON_API_URL
import com.msd.network.ApiConstants.ICON_API_URL_NAME
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun List<CoinData>.toDomain(): List<LatestCoinDomain> {
    return mapNotNull { data ->
        with(data) {
            cmcRank?.let {
                id?.let {
                    LatestCoinDomain(
                        id = id,
                        name = name.orEmpty(),
                        symbol = symbol.orEmpty(),
                        summary = toGrowthPercentage(),
                        growth = toGrowth(),
                        price = toFormattedPrice(),
                        icon = toIconUrl(),
                        cmcRank = cmcRank
                    )
                }
            }
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
    }.orEmpty()
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
