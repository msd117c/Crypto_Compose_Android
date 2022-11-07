package com.msd117.cryptocompose.detail.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContractAddress(
    @Json(name = "contract_address") val contractAddress: String,
    val platform: Platform
)
