package com.msd.latest_coins.list.presenter.mapper

import androidx.compose.ui.graphics.Color
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.latest_coins_list.model.GrowthDomain
import com.msd.latest_coins_list.model.LatestCoinDomain

object LatestCoinMapper {

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

}
