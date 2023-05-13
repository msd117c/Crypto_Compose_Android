package com.msd.data.coin_details.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Urls(
    val website: List<String>,
    val twitter: List<String>,
    @Json(name = "message_board") val messageBoard: List<String>,
    val chat: List<String>,
    val facebook: List<String>,
    val explorer: List<String>,
    val reddit: List<String>,
    @Json(name = "technical_doc") val technicalDoc: List<String>,
    @Json(name = "source_code") val sourceCode: List<String>,
    val announcement: List<String>
)
