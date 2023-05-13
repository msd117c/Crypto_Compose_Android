package com.msd.data.latest_coins

import com.msd.data.latest_coins.model.CoinData
import com.msd.data.latest_coins.model.Quote
import com.msd.data.latest_coins.model.Usd
import com.msd.domain.latest_coins_list.model.GrowthDomain
import com.msd.domain.latest_coins_list.model.LatestCoinDomain

object TestDataBuilder {

    fun buildCoinData(
        id: Int? = 1,
        name: String? = "Name",
        symbol: String? = "Symbol",
        slug: String? = "Slug",
        cmcRank: Int? = 0,
        quote: Quote? = buildQuote()
    ): CoinData {
        return CoinData(
            id = id,
            name = name,
            symbol = symbol,
            slug = slug,
            cmcRank = cmcRank,
            quote = quote
        )
    }

    fun buildQuote(usd: Usd? = buildUsd()): Quote = Quote(usd = usd)

    fun buildUsd(
        price: Double? = 0.0,
        percentChange1h: Double? = 0.0
    ): Usd {
        return Usd(
            price = price,
            percentChange1h = percentChange1h
        )
    }

    fun buildLatestCoinDomain(
        id: Int = 1,
        name: String = "Name",
        symbol: String = "Symbol",
        summary: String = "Summary",
        growth: GrowthDomain = GrowthDomain.NONE,
        price: String = "0",
        icon: String = "icon",
        cmcRank: Int = 1,
    ): LatestCoinDomain {
        return LatestCoinDomain(
            id = id,
            name = name,
            symbol = symbol,
            summary = summary,
            growth = growth,
            price = price,
            icon = icon,
            cmcRank = cmcRank,
        )
    }
}