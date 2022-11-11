package com.msd117.cryptocompose.latest.presenter.model

import androidx.compose.ui.graphics.Color

data class LatestCoin(
    val id: Int,
    val name: String,
    val symbol: String,
    val summary: String,
    val growthColor: Color,
    val price: String,
    val icon: String,
    val cmcRank: String
)
