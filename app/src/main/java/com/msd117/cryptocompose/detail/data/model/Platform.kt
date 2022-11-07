package com.msd117.cryptocompose.detail.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Platform (val name : String, val coin : Coin?)
