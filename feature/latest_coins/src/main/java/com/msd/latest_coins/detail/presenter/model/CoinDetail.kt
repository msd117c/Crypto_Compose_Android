package com.msd.latest_coins.detail.presenter.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CoinDetail(
    val name: String,
    val symbol: String,
    val description: String,
    val dateAdded: String,
    val tagNames: List<String>,
    val technicalButtons: List<TechnicalButtons>,
    val urlButtons: List<UrlButton>
) {

    data class TechnicalButtons(
        @DrawableRes val icon: Int,
        @StringRes val label: Int,
        val url: String
    )

    data class UrlButton(@DrawableRes val icon: Int, val url: String)
}
