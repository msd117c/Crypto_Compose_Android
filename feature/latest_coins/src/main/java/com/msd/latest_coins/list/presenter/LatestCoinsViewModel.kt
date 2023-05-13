package com.msd.latest_coins.list.presenter

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.msd.core.presentation.BaseViewModel
import com.msd.latest_coins.list.presenter.helper.FetchLatestModelsHelper
import com.msd.latest_coins.list.presenter.helper.GetLatestCoinSortByOptionsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestCoinsViewModel @Inject constructor(
    private val getLatestCoinSortByOptionsHelper: GetLatestCoinSortByOptionsHelper,
    private val fetchLatestModelsHelper: FetchLatestModelsHelper
) : BaseViewModel<LatestCoinsState>() {

    override val state: MutableStateFlow<LatestCoinsState> = MutableStateFlow(initialState)

    override fun initialize() {
        super.initialize()

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
        viewModelScope.launch {
            state.value = LatestCoinsState.Loading
            try {
                val sortBy = sortByOptions.first { it.selected }
                val latestCoins = fetchLatestModelsHelper(sortBy).cachedIn(viewModelScope)
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
