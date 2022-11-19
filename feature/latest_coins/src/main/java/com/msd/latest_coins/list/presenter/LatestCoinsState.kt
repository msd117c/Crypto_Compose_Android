package com.msd.latest_coins.list.presenter

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.core.presentation.State
import kotlinx.coroutines.flow.Flow

sealed class LatestCoinsState : State {

    object Uninitialized : LatestCoinsState()
    object Loading : LatestCoinsState()
    data class Loaded(
        val sortByOptions: List<SortBy>,
        val latestCoins: Flow<PagingData<LatestCoin>>
    ) : LatestCoinsState() {

        data class SortBy(
            val id: String,
            @StringRes val label: Int,
            val selected: Boolean
        )
    }

    object Error : LatestCoinsState()
}

val initialState = LatestCoinsState.Uninitialized
