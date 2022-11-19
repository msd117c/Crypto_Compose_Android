package com.msd.latest_coins.model

import com.squareup.moshi.Json

data class LatestCoinsResponse(
    @Json(name = "data")
    val coinDataList: List<CoinData>?
)
