package com.msd117.cryptocompose.presentation.latest.presenter

import androidx.lifecycle.ViewModel
import com.msd117.cryptocompose.presentation.latest.helper.FetchLatestModelsHelper
import com.msd117.cryptocompose.utils.getViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestCoinsViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val fetchLatestModelsHelper: FetchLatestModelsHelper
) : ViewModel() {

    private val scope = getViewModelScope(coroutineScope)

    private val state: MutableStateFlow<LatestCoinsState> = MutableStateFlow(initialState)
    fun getState(): Flow<LatestCoinsState> = state

    init {
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
