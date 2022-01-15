package com.msd117.cryptocompose.presentation.latest.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsViewModel
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.utils.NavigationConstants

@Composable
fun LatestCoinsView(viewModel: LatestCoinsViewModel, navController: NavController) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(
                latestCoins = state.latestCoins,
                onClick = { symbol ->
                    val route = NavigationConstants.CoinDetailsRoute.replace(
                        NavigationConstants.CoinDetailsRouteSymbolArgToReplace,
                        symbol
                    )
                    navController.navigate(route)
                }
            )
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
