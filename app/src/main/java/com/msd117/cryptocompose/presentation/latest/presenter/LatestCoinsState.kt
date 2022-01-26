package com.msd117.cryptocompose.presentation.latest.presenter

import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.utils.State

sealed class LatestCoinsState : State {

    object Uninitialized : LatestCoinsState()
    object Loading : LatestCoinsState()
    data class Loaded(val latestCoins: List<LatestCoin>) : LatestCoinsState()
    object Error : LatestCoinsState()
}

val initialState = LatestCoinsState.Uninitialized