package com.msd117.cryptocompose.data.model.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContractAddress(
    @Json(name = "contract_address") val contractAddress: String,
    val platform: Platform
)
