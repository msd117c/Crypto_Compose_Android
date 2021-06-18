package com.msd117.cryptocompose.presentation.latest.helper

import com.msd117.cryptocompose.domain.usecase.latest.FetchLatestUseCase
import com.msd117.cryptocompose.presentation.latest.model.Btc
import com.msd117.cryptocompose.presentation.latest.model.Eth
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.presentation.latest.model.Usd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.msd117.cryptocompose.data.model.latest.Btc as DomainBtc
import com.msd117.cryptocompose.data.model.latest.Eth as DomainEth
import com.msd117.cryptocompose.data.model.latest.Usd as DomainUsd

class FetchLatestModelsHelper @Inject constructor(private val fetchLatestUseCase: FetchLatestUseCase) {

    suspend operator fun invoke(): List<LatestCoin> = withContext(Dispatchers.IO) {
        val latestResponse = fetchLatestUseCase()
        latestResponse.data?.map { data ->
            LatestCoin(
                id = data.id,
                name = data.name ?: "",
                symbol = data.symbol ?: "",
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