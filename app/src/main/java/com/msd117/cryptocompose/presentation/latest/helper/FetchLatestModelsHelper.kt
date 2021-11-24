package com.msd117.cryptocompose.presentation.latest.helper

import com.msd117.cryptocompose.domain.usecase.latest.FetchLatestUseCase
import com.msd117.cryptocompose.presentation.latest.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import javax.inject.Inject
import com.msd117.cryptocompose.data.model.latest.Btc as DomainBtc
import com.msd117.cryptocompose.data.model.latest.Eth as DomainEth
import com.msd117.cryptocompose.data.model.latest.Usd as DomainUsd

private const val ICON_API_URL = "https://cryptoicon-api.vercel.app/api/icon/"

class FetchLatestModelsHelper @Inject constructor(private val fetchLatestUseCase: FetchLatestUseCase) {

    suspend operator fun invoke(): List<LatestCoin> = withContext(Dispatchers.IO) {
        val latestResponse = fetchLatestUseCase()
        latestResponse.data?.map { data ->
            val growthPercent = data.quote?.usd?.percentChange1h?.let { percent ->
                val formattedPercent = BigDecimal(percent).setScale(2, RoundingMode.HALF_EVEN)
                if (formattedPercent > BigDecimal.ZERO) {
                    "+$formattedPercent%"
                } else {
                    "$formattedPercent%"
                }
            } ?: ""
            val growth = when {
                data.quote?.usd?.percentChange1h ?: 0.0 > 0.0 -> Growth.POSITIVE
                data.quote?.usd?.percentChange1h ?: 0.0 < 0.0 -> Growth.NEGATIVE
                else -> Growth.NONE
            }
            val price = data.quote?.usd?.price ?: 0.0
            val formattedPrice = StringBuilder()
            val formatter = Formatter(formattedPrice, Locale.US)
            formatter.format("$ %(,.2f", price)

            LatestCoin(
                id = data.id,
                name = data.name ?: "",
                symbol = data.symbol ?: "",
                summary = growthPercent,
                growth = growth,
                price = formattedPrice.toString(),
                icon = ICON_API_URL + data.symbol?.lowercase(),
                slug = data.slug,
                cmcRank = data.cmcRank,
                numMarketPairs = data.numMarketPairs,
                circulatingSupply = data.circulatingSupply,
                totalSupply = data.totalSupply,
                maxSupply = data.maxSupply,
                lastUpdated = data.lastUpdated,
                dateAdded = data.dateAdded,
                tags = data.tags,
                platform = data.platform,
                btc = data.quote?.btc?.toPresentationBtc(),
                eth = data.quote?.eth?.toPresentationEth(),
                usd = data.quote?.usd?.toPresentationUsd()
            )
        } ?: emptyList()
    }

    private fun DomainBtc.toPresentationBtc() = Btc(
        price = price,
        volume24h = volume24h,
        percentChange1h = percentChange1h,
        percentChange24h = percentChange24h,
        percentChange7d = percentChange7d,
        marketCap = marketCap,
        lastUpdated = lastUpdated
    )

    private fun DomainEth.toPresentationEth() = Eth(
        price = price,
        volume24h = volume24h,
        percentChange1h = percentChange1h,
        percentChange24h = percentChange24h,
        percentChange7d = percentChange7d,
        marketCap = marketCap,
        lastUpdated = lastUpdated
    )

    private fun DomainUsd.toPresentationUsd() = Usd(
        price = price,
        volume24h = volume24h,
        percentChange1h = percentChange1h,
        percentChange24h = percentChange24h,
        percentChange7d = percentChange7d,
        marketCap = marketCap,
        lastUpdated = lastUpdated,
        isPositive = percentChange1h ?: 0.0 > percentChange24h ?: 0.0
    )
}
