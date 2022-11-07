package com.msd117.cryptocompose.detail.presenter

import com.msd117.cryptocompose.detail.presenter.model.CoinDetail
import com.msd117.cryptocompose.utils.State

sealed class CoinDetailState : State {

    object Uninitialized : CoinDetailState()
    object Loading : CoinDetailState()
    object Error : CoinDetailState()
    data class Loaded(val coinDetail: CoinDetail) : CoinDetailState()
}

val initialState = CoinDetailState.Uninitialized
