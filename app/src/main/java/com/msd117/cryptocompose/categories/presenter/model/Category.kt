package com.msd117.cryptocompose.categories.presenter.model

data class Category(
    val id: String,
    val name: String,
    val title: String,
    val description: String?,
    val numTokens: Int?,
    val avgPriceChange: Double?,
    val marketCap: Double?,
    val marketCapChange: Double?,
    val volume: Double?,
    val volumeChange: Double?,
    val lastUpdated: String?
)
