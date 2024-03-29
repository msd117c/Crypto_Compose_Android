package com.msd.network

object ApiConstants {
    const val HEADER_API_KEY_PARAMETER_NAME = "X-CMC_PRO_API_KEY"
    private const val VERSION = "v1"
    private const val CRYPTOCURRENCY = "cryptocurrency/"
    const val BASE_URL = "https://pro-api.coinmarketcap.com/$VERSION/$CRYPTOCURRENCY"

    const val QUERY_START_PARAMETER = "start"
    const val QUERY_LIMIT_PARAMETER = "limit"

    const val LATEST_COINS_ENDPOINT = "listings/latest"
    const val LATEST_COINS_SORT_PARAMETER = "sort"

    const val COIN_DETAIL_ENDPOINT = "info"
    const val COIN_DETAIL_SYMBOL_PARAMETER = "symbol"

    const val CATEGORIES_ENDPOINT = "categories"

    const val ICON_API_URL_NAME = "{name}"
    const val ICON_API_URL =
        "https://raw.githubusercontent.com/ErikThiart/cryptocurrency-icons/master/64/$ICON_API_URL_NAME.png"
}
