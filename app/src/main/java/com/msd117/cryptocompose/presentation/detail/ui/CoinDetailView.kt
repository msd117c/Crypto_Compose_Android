package com.msd117.cryptocompose.presentation.detail.ui

import android.app.Activity
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.presentation.main.ui.MainActivity
import dagger.hilt.android.EntryPointAccessors

@ExperimentalTransitionApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun coinDetailViewModel(symbol: String): CoinDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).coinDetailViewModelFactory()

    return viewModel(factory = CoinDetailViewModel.provideFactory(factory, symbol))
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun CoinDetailView(viewModel: CoinDetailViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)
    CoinDetailLoadingView("https://cryptoicon-api.vercel.app/api/icon/btc")
    //Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
    //    when (state) {
    //        is CoinDetailState.Loading -> CoinDetailLoadingView("https://cryptoicon-api.vercel.app/api/icon/btc")
    //        is CoinDetailState.Error -> CoinDetailErrorView()
    //        is CoinDetailState.Loaded -> CoinDetailLoadedView(state.coinDetail)
    //    }
    //}
}
