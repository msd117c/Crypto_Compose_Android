package com.msd117.cryptocompose.presentation.detail.presenter

import androidx.lifecycle.ViewModel
import com.msd117.cryptocompose.presentation.detail.helper.FetchCoinDetailInfoHelper
import com.msd117.cryptocompose.utils.getViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val fetchCoinDetailInfoHelper: FetchCoinDetailInfoHelper
) : ViewModel() {

    private val scope = getViewModelScope(coroutineScope)

    private val state: MutableStateFlow<CoinDetailState> = MutableStateFlow(initialState)
    fun getState(): Flow<CoinDetailState> = state

    fun fetchCoinDetail(symbol: String) {
        scope.launch {
            state.value = CoinDetailState.Loading
            try {
                val coinDetail = fetchCoinDetailInfoHelper(symbol)
                state.value = CoinDetailState.Loaded(coinDetail)
            } catch (exception: Exception) {
                state.value = CoinDetailState.Error
            }
        }
    }
}
