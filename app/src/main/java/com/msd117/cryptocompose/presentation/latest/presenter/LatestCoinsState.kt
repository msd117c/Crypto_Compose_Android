package com.msd117.cryptocompose.presentation.latest.presenter

import com.msd117.cryptocompose.presentation.latest.model.LatestCoin

sealed class LatestCoinsState {
    object Loading : LatestCoinsState()
    data class Loaded(val latestCoins: List<LatestCoin>) : LatestCoinsState()
    object Error : LatestCoinsState()
}

val initialState = LatestCoinsState.Loading