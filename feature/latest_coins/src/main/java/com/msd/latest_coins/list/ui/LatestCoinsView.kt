package com.msd.latest_coins.list.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.msd.latest_coins.list.presenter.LatestCoinsState
import com.msd.latest_coins.list.presenter.LatestCoinsViewModel
import com.msd.latest_coins.list.presenter.initialState
import com.msd.core.ui.theme.BaseView
import com.msd.core.navigation.NavigationConstants.CoinDetailsRoute
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteIconArgToReplace
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteNameArgToReplace
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteSymbolArgToReplace
import com.msd.core.navigation.NavigationEvent.Route
import java.net.URLEncoder.encode
import kotlin.text.Charsets.UTF_8

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LatestCoinsView(viewModel: LatestCoinsViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(
                sortByOptionsProvider = state::sortByOptions,
                latestCoinsProvider = state::latestCoins,
                onClick = { symbol, icon, name ->
                    val route = CoinDetailsRoute
                        .replace(CoinDetailsRouteSymbolArgToReplace, symbol)
                        .replace(CoinDetailsRouteIconArgToReplace, encode(icon, UTF_8.name()))
                        .replace(CoinDetailsRouteNameArgToReplace, name)

                    viewModel.navigate(Route(route))
                },
                onSortByClicked = viewModel::onSortByClicked
            )
            is LatestCoinsState.Uninitialized -> Unit
        }
    }
}

@Preview
@Composable
fun LatestCoinViewPreview() {
    BaseView { LatestCoinLoadingView() }
}
