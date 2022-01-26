package com.msd117.cryptocompose.presentation.latest.presenter

import androidx.paging.cachedIn
import com.msd117.cryptocompose.presentation.latest.helper.FetchLatestModelsHelper
import com.msd117.cryptocompose.presentation.latest.helper.GetLatestCoinSortByOptionsHelper
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

    fun initialize() {
        if (state.value !is LatestCoinsState.Uninitialized) return

        val sortByOptions = getLatestCoinSortByOptionsHelper()
        fetchLatestCoins(sortByOptions)
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

            fetchLatestCoins(sortByOptions)
        }
    }

    private fun fetchLatestCoins(sortByOptions: List<LatestCoinsState.Loaded.SortBy>) {
        scope.launch {
            state.value = LatestCoinsState.Loading
            try {
                val sortBy = sortByOptions.first { it.selected }
                val latestCoins = fetchLatestModelsHelper(sortBy).cachedIn(scope)
                state.value = LatestCoinsState.Loaded(
                    sortByOptions = sortByOptions,
                    latestCoins = latestCoins
                )
            } catch (exception: Exception) {
                state.value = LatestCoinsState.Error
            }
        }
    }
}
