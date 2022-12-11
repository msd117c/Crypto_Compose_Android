package com.msd.data.latest_coins.model

import com.squareup.moshi.Json

data class CoinData(
    val id: Int?,
    val name: String?,
    val symbol: String?,
    val slug: String?,
    @Json(name = "cmc_rank")
    val cmcRank: Int?,
    val quote: Quote?
)
