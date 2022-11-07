package com.msd117.cryptocompose.detail.presenter.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CoinDetail(
    val logo: String,
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val description: String,
    val notice: String,
    val dateAdded: String,
    val tags: List<String>,
    val category: String,
    val platform: CoinPlatform?,
    val tagNames: List<String>,
    val tagGroups: List<String>,
    val twitterUsername: String,
    val isHidden: Int,
    val dateLaunched: String?,
    val contractAddress: List<ContractAddress>,
    val selfReportedCirculatingSupply: String?,
    val selfReportedTags: List<String>?,
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
