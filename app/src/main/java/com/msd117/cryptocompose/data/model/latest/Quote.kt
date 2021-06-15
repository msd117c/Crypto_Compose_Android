package com.msd117.cryptocompose.data.model.latest

import com.squareup.moshi.Json

data class Quote(
    @Json(name = "USD")
    val usd: Usd? = null,
    @Json(name = "BTC")
    val btc: Btc? = null,
    @Json(name = "ETH")
    val eth: Eth? = null
)