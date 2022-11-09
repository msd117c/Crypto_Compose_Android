package com.msd117.cryptocompose.latest.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.msd117.cryptocompose.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.latest.presenter.LatestCoinsViewModel
import com.msd117.cryptocompose.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRoute
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteIconArgToReplace
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteNameArgToReplace
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteSymbolArgToReplace
import java.net.URLEncoder.encode
import kotlin.text.Charsets.UTF_8

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LatestCoinsView(viewModel: LatestCoinsViewModel, navController: NavController) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)
    viewModel.initialize()

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(
                sortByOptions = state.sortByOptions,
                latestCoins = state.latestCoins,
                onClick = { symbol, icon, name ->
                    val route = CoinDetailsRoute
                        .replace(CoinDetailsRouteSymbolArgToReplace, symbol)
                        .replace(CoinDetailsRouteIconArgToReplace, encode(icon, UTF_8.name()))
                        .replace(CoinDetailsRouteNameArgToReplace, name)

                    navController.navigate(route)
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
