package com.msd117.cryptocompose.presentation.detail.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msd117.cryptocompose.presentation.detail.helper.FetchCoinDetailInfoHelper
import com.msd117.cryptocompose.utils.NavigationConstants
import com.msd117.cryptocompose.utils.getViewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel @AssistedInject constructor(
    coroutineScope: CoroutineScope?,
    private val fetchCoinDetailInfoHelper: FetchCoinDetailInfoHelper,
    @Assisted(NavigationConstants.CoinDetailsRouteSymbolArg) val symbol: String,
    @Assisted(NavigationConstants.CoinDetailsRouteIconArg) val icon: String,
    @Assisted(NavigationConstants.CoinDetailsRouteNameArg) val name: String
) : ViewModel() {

    private val scope = getViewModelScope(coroutineScope)

    private val state: MutableStateFlow<CoinDetailState> = MutableStateFlow(initialState)
    fun getState(): Flow<CoinDetailState> = state

    init {
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

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(NavigationConstants.CoinDetailsRouteSymbolArg) symbol: String,
            @Assisted(NavigationConstants.CoinDetailsRouteIconArg) icon: String,
            @Assisted(NavigationConstants.CoinDetailsRouteNameArg) name: String
        ): CoinDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            symbol: String,
            icon: String,
            name: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(symbol, icon, name) as T
            }
        }
    }
}
