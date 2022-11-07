package com.msd117.cryptocompose.detail.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val urls: Urls,
    val logo: String,
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val description: String,
    val notice: String,
    @Json(name = "date_added") val dateAdded: String,
    val tags: List<String>,
    val platform: Platform?,
    val category: String,
    val subreddit: String,
    @Json(name = "tag-names") val tagNames: List<String>,
    @Json(name = "tag-groups") val tagGroups: List<String>,
    @Json(name = "twitter_username") val twitterUsername: String,
    @Json(name = "is_hidden") val isHidden: Int,
    @Json(name = "date_launched") val dateLaunched: String?,
    @Json(name = "contract_address") val contractAddress: List<ContractAddress>,
    @Json(name = "self_reported_circulating_supply") val selfReportedCirculatingSupply: String?,
    @Json(name = "self_reported_tags") val selfReportedTags: List<String>?
)
