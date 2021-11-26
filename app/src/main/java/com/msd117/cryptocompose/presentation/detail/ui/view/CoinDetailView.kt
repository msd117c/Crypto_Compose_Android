package com.msd117.cryptocompose.presentation.detail.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailState
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import kotlinx.coroutines.flow.Flow

@Composable
fun CoinDetailView(stateFlow: Flow<CoinDetailState>) {
    val currentState by stateFlow.collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is CoinDetailState.Loading -> CoinDetailLoadingView()
            is CoinDetailState.Error -> CoinDetailErrorView()
            is CoinDetailState.Loaded -> CoinDetailLoadedView(state.coinDetail)
        }
    }
}
