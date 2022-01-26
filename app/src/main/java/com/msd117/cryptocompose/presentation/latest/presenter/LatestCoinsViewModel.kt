package com.msd117.cryptocompose.presentation.latest.presenter

import com.msd117.cryptocompose.presentation.latest.helper.FetchLatestModelsHelper
import com.msd117.cryptocompose.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestCoinsViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val fetchLatestModelsHelper: FetchLatestModelsHelper
) : BaseViewModel<LatestCoinsState>(coroutineScope) {

    override val state: MutableStateFlow<LatestCoinsState> = MutableStateFlow(initialState)

    fun initialize() {
        if (state.value !is LatestCoinsState.Uninitialized) return

        scope.launch {
            state.value = LatestCoinsState.Loading
            val latestCoins = fetchLatestModelsHelper()
            if (latestCoins.isEmpty()) {
                state.value = LatestCoinsState.Error
            } else {
                state.value = LatestCoinsState.Loaded(latestCoins = latestCoins)
            }
        }
    }
}
