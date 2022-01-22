package com.msd117.cryptocompose.utils

object NavigationConstants {
    const val SplashRoute = "splash"
    const val MainRoute = "main"
    const val LatestCoinsRoute = "latest_coins"

    const val CoinDetailsRouteSymbolArg = "symbol"
    const val CoinDetailsRouteSymbolArgToReplace = "{$CoinDetailsRouteSymbolArg}"

    const val CoinDetailsRouteIconArg = "icon"
    const val CoinDetailsRouteIconArgToReplace = "{$CoinDetailsRouteIconArg}"

    const val CoinDetailsRouteNameArg = "name"
    const val CoinDetailsRouteNameArgToReplace = "{$CoinDetailsRouteNameArg}"

    const val CoinDetailsRoute =
        "coin_details/$CoinDetailsRouteSymbolArgToReplace/$CoinDetailsRouteIconArgToReplace/$CoinDetailsRouteNameArgToReplace"
}