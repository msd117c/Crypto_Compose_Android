package com.msd.domain.coin_details.model

data class CoinDetailDomain(
    val name: String,
    val symbol: String,
    val description: String,
    val dateAdded: String,
    val tagNames: List<String>,
    val urls: UrlsDomain
)
