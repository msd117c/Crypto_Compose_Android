package com.msd.coin_details.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDetail(
    val urls: Urls,
    val name: String,
    val symbol: String,
    val slug: String,
    val description: String,
    @Json(name = "date_added") val dateAdded: String,
    @Json(name = "tag-names") val tagNames: List<String>
)
