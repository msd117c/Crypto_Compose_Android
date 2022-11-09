package com.msd117.cryptocompose.latest.data.model

import com.squareup.moshi.Json

data class LatestCoinsResponse(
    @Json(name = "data")
    val coinDataList: List<CoinData>?,
    val status: Status?
)
