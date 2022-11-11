package com.msd117.cryptocompose.detail.domain.model

data class CoinDetailDomain(
    val name: String,
    val symbol: String,
    val description: String,
    val dateAdded: String,
    val tagNames: List<String>,
    val urls: UrlsDomain
)
