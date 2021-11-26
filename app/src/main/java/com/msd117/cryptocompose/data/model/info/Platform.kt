package com.msd117.cryptocompose.data.model.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Platform (val name : String, val coin : Coin?)
