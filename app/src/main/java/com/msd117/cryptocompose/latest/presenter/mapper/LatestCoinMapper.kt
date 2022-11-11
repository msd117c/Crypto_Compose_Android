package com.msd117.cryptocompose.latest.presenter.mapper

import androidx.compose.ui.graphics.Color
import com.msd117.cryptocompose.latest.domain.model.GrowthDomain
import com.msd117.cryptocompose.latest.domain.model.LatestCoinDomain
import com.msd117.cryptocompose.latest.presenter.model.LatestCoin

fun List<LatestCoinDomain>.toPresentation(): List<LatestCoin> {
    return map { latestCoinDomain ->
        with(latestCoinDomain) {
            LatestCoin(
                id = id,
                name = name,
                symbol = symbol,
                summary = summary,
                growthColor = growth.toPresentation(),
                price = price,
                icon = icon,
                cmcRank = cmcRank.toString()
            )
        }
    }
}

private fun GrowthDomain.toPresentation() = when (this) {
    GrowthDomain.POSITIVE -> Color.Green
    GrowthDomain.NEGATIVE -> Color.Red
    GrowthDomain.NONE -> Color.Gray
}
