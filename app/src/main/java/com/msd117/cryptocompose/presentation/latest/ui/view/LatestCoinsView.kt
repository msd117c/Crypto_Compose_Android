package com.msd117.cryptocompose.presentation.latest.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.msd117.cryptocompose.presentation.latest.model.Growth
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import kotlinx.coroutines.flow.Flow

@Composable
fun LatestCoinsView(stateFlow: Flow<LatestCoinsState>) {
    val currentState by stateFlow.collectAsState(initial = initialState)
    var showLatestCoinDetail by remember { mutableStateOf<LatestCoin?>(null) }
    val onClick: (LatestCoin) -> Unit = { latestCoin ->
        showLatestCoinDetail = latestCoin
    }
    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(
                latestCoins = state.latestCoins,
                onClick = onClick
            )
        }
    }

    showLatestCoinDetail?.let { latestCoin ->
        Dialog(onDismissRequest = { showLatestCoinDetail = null }) {
            LatestCoinDetailView(latestCoin = latestCoin)
        }
    }
}

@Preview
@Composable
fun LatestCoinViewPreview() {
    BaseView {
        LatestCoinDetailView(
            latestCoin = LatestCoin(
                name = "Bitcoin",
                symbol = "BTC",
                summary = "BTC (+0.5%)",
                growth = Growth.POSITIVE,
                price = "50",
                icon = ""
            )
        )
    }
}
