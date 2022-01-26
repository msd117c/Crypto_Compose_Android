package com.msd117.cryptocompose.presentation.latest.presenter

import androidx.paging.cachedIn
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
            try {
                val latestCoins = fetchLatestModelsHelper().cachedIn(scope)
                state.value = LatestCoinsState.Loaded(latestCoins = latestCoins)
            } catch (exception: Exception) {
                state.value = LatestCoinsState.Error
            }
        }
    }
}
