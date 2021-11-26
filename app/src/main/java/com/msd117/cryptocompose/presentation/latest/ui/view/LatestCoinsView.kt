package com.msd117.cryptocompose.presentation.latest.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import kotlinx.coroutines.flow.Flow

@Composable
fun LatestCoinsView(stateFlow: Flow<LatestCoinsState>, onCoinClicked: (String) -> Unit) {
    val currentState by stateFlow.collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(
                latestCoins = state.latestCoins,
                onClick = onCoinClicked
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
