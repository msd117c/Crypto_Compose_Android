package com.msd117.cryptocompose.data.model.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coin(
    val id: Int,
    val name: String?,
    val symbol: String?,
    val slug: String?
)
