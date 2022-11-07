package com.msd117.cryptocompose.detail.domain.model

data class CoinInfoDomain(
    val logo: String,
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val description: String,
    val notice: String,
    val dateAdded: String,
    val tags: List<String>,
    val category: String,
    val platform: CoinPlatformDomain?,
    val tagNames: List<String>,
    val tagGroups: List<String>,
    val twitterUsername: String,
    val isHidden: Int,
    val dateLaunched: String?,
    val contractAddress: List<ContractAddressDomain>,
    val selfReportedCirculatingSupply: String?,
    val selfReportedTags: List<String>?,
    val urls: UrlsDomain
)
