package com.msd117.cryptocompose.utils

object ApiConstants {
    const val headerApiKeyParameterName = "X-CMC_PRO_API_KEY"
    private const val version = "v1"
    private const val cryptocurrency = "cryptocurrency/"
    const val baseUrl = "https://pro-api.coinmarketcap.com/$version/$cryptocurrency"

    const val latestEndpoint = "listings/latest"
}
