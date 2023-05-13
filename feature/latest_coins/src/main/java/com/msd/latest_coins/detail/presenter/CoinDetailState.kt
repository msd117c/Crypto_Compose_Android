package com.msd.latest_coins.detail.presenter

import com.msd.latest_coins.detail.presenter.CoinDetailState.Companion.EMPTY_COIN_DATA
import com.msd.latest_coins.detail.presenter.model.CoinDetail
import com.msd.core.presentation.State

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

    override fun isUninitialized(): Boolean = this is Uninitialized
}

val initialState = CoinDetailState.Uninitialized(EMPTY_COIN_DATA)
