package com.msd117.cryptocompose.presentation.detail.ui.view

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailState
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.presentation.detail.ui.CoinDetailErrorView
import com.msd117.cryptocompose.presentation.detail.ui.CoinDetailLoadedView
import com.msd117.cryptocompose.presentation.detail.ui.CoinDetailLoadingView
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.presentation.main.ui.MainActivity
import dagger.hilt.android.EntryPointAccessors

@ExperimentalMaterialApi
@Composable
fun coinDetailViewModel(symbol: String): CoinDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).coinDetailViewModelFactory()

    return viewModel(factory = CoinDetailViewModel.provideFactory(factory, symbol))
}

@ExperimentalMaterialApi
@Composable
fun CoinDetailView(viewModel: CoinDetailViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is CoinDetailState.Loading -> CoinDetailLoadingView()
            is CoinDetailState.Error -> CoinDetailErrorView()
            is CoinDetailState.Loaded -> CoinDetailLoadedView(state.coinDetail)
        }
    }
}
