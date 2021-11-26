package com.msd117.cryptocompose.presentation.detail.presenter

import com.msd117.cryptocompose.presentation.detail.model.CoinDetail

sealed class CoinDetailState {

    object Loading : CoinDetailState()
    object Error : CoinDetailState()
    data class Loaded(val coinDetail: CoinDetail) : CoinDetailState()
}

val initialState = CoinDetailState.Loading
