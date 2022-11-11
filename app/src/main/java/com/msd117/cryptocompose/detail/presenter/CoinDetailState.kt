package com.msd117.cryptocompose.detail.presenter

import com.msd117.cryptocompose.detail.presenter.CoinDetailState.Companion.EMPTY_COIN_DATA
import com.msd117.cryptocompose.detail.presenter.model.CoinDetail
import com.msd117.cryptocompose.utils.State

sealed class CoinDetailState(open val coinData: CoinData = EMPTY_COIN_DATA) : State {

    companion object {
        val EMPTY_COIN_DATA = CoinData("", "", "")
    }

    data class CoinData(val symbol: String, val icon: String, val name: String)

    data class Uninitialized(override val coinData: CoinData) : CoinDetailState(coinData)
    data class Loading(override val coinData: CoinData) : CoinDetailState(coinData)
    data class Error(override val coinData: CoinData) : CoinDetailState(coinData)
    data class Loaded(
        override val coinData: CoinData,
        val coinDetail: CoinDetail
    ) : CoinDetailState(coinData)
}

val initialState = CoinDetailState.Uninitialized(EMPTY_COIN_DATA)
