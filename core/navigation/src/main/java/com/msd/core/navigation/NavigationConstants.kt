package com.msd.core.navigation

object NavigationConstants {
    const val MainRoute = "main"
    const val LatestCoinsRoute = "latest_coins"
    const val CategoriesRoute = "categories"

    const val CoinDetailsRouteSymbolArg = "symbol"
    const val CoinDetailsRouteSymbolArgToReplace = "{$CoinDetailsRouteSymbolArg}"

    const val CoinDetailsRouteIconArg = "icon"
    const val CoinDetailsRouteIconArgToReplace = "{$CoinDetailsRouteIconArg}"

    const val CoinDetailsRouteNameArg = "name"
    const val CoinDetailsRouteNameArgToReplace = "{$CoinDetailsRouteNameArg}"

    const val CoinDetailsRoute =
        "coin_details/$CoinDetailsRouteSymbolArgToReplace/$CoinDetailsRouteIconArgToReplace/$CoinDetailsRouteNameArgToReplace"
}
