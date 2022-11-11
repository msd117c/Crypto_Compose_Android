package com.msd117.cryptocompose.latest.presenter.mapper

import com.msd117.cryptocompose.latest.domain.model.GrowthDomain
import com.msd117.cryptocompose.latest.domain.model.LatestCoinDomain
import com.msd117.cryptocompose.latest.presenter.model.Growth
import com.msd117.cryptocompose.latest.presenter.model.LatestCoin

fun List<LatestCoinDomain>.toPresentation(): List<LatestCoin> {
    return map { latestCoinDomain ->
        with(latestCoinDomain) {
            LatestCoin(
                name = name,
                symbol = symbol,
                summary = summary,
                growth = growth.toPresentation(),
                price = price,
                icon = icon,
                cmcRank = cmcRank
            )
        }
    }
}

private fun GrowthDomain.toPresentation() = when (this) {
    GrowthDomain.POSITIVE -> Growth.POSITIVE
    GrowthDomain.NEGATIVE -> Growth.NEGATIVE
    GrowthDomain.NONE -> Growth.NONE
}
