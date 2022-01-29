package com.msd117.cryptocompose.presentation.latest.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsViewModel
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.utils.NavigationConstants
import java.net.URLEncoder

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
                    val route = NavigationConstants.CoinDetailsRoute
                        .replace(
                            NavigationConstants.CoinDetailsRouteSymbolArgToReplace,
                            symbol
                        )
                        .replace(
                            NavigationConstants.CoinDetailsRouteIconArgToReplace,
                            URLEncoder.encode(icon, Charsets.UTF_8.name())
                        )
                        .replace(
                            NavigationConstants.CoinDetailsRouteNameArgToReplace,
                            name
                        )
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
    BaseView {
        LatestCoinLoadingView()
    }
}
