package com.msd117.cryptocompose.latest.presenter

import android.util.Log
import androidx.paging.cachedIn
import com.msd117.cryptocompose.latest.presenter.helper.FetchLatestModelsHelper
import com.msd117.cryptocompose.latest.presenter.helper.GetLatestCoinSortByOptionsHelper
import com.msd117.cryptocompose.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestCoinsViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val getLatestCoinSortByOptionsHelper: GetLatestCoinSortByOptionsHelper,
    private val fetchLatestModelsHelper: FetchLatestModelsHelper
) : BaseViewModel<LatestCoinsState>(coroutineScope) {

    override val state: MutableStateFlow<LatestCoinsState> = MutableStateFlow(initialState)

    override fun initialize() {
        Log.d("LATEST", "INIT")
        if (state.value !is LatestCoinsState.Uninitialized) return
        Log.d("LATEST", "INIT OK")
        val sortByOptions = getLatestCoinSortByOptionsHelper()
        fetchLatestCoins(sortByOptions = sortByOptions)
    }

    fun onSortByClicked(sortById: String, isSelected: Boolean) {
        (state.value as? LatestCoinsState.Loaded)?.let { loaded ->
            val updatedSortByOptions = loaded.sortByOptions.map { sortBy ->
                if (sortBy.id == sortById) {
                    sortBy.copy(selected = isSelected)
                } else {
                    sortBy.copy(selected = false)
                }
            }.sortedBy { !it.selected }

            val sortByOptions = if (updatedSortByOptions.all { !it.selected }) {
                getLatestCoinSortByOptionsHelper()
            } else {
                updatedSortByOptions
            }

            fetchLatestCoins(loaded, sortByOptions)
        }
    }

    private fun fetchLatestCoins(
        loaded: LatestCoinsState.Loaded? = null,
        sortByOptions: List<LatestCoinsState.Loaded.SortBy>
    ) {
        scope.launch {
            state.value = LatestCoinsState.Loading
            try {
                val sortBy = sortByOptions.first { it.selected }
                val latestCoins = fetchLatestModelsHelper(sortBy).cachedIn(scope)
                state.value = loaded?.copy(
                    sortByOptions = sortByOptions,
                    latestCoins = latestCoins
                ) ?: LatestCoinsState.Loaded(sortByOptions, latestCoins)
            } catch (exception: Exception) {
                state.value = LatestCoinsState.Error
            }
        }
    }
}
