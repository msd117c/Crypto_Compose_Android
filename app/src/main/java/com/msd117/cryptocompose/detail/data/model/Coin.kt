package com.msd117.cryptocompose.detail.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coin(
    val id: Int,
    val name: String?,
    val symbol: String?,
    val slug: String?
)
