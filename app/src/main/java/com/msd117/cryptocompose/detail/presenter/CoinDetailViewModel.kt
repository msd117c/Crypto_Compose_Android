package com.msd117.cryptocompose.detail.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msd117.cryptocompose.detail.presenter.helper.FetchCoinDetailHelper
import com.msd117.cryptocompose.utils.BaseViewModel
import com.msd117.cryptocompose.utils.NavigationConstants
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel @AssistedInject constructor(
    coroutineScope: CoroutineScope?,
    private val fetchCoinDetailHelper: FetchCoinDetailHelper,
    @Assisted(NavigationConstants.CoinDetailsRouteSymbolArg) symbol: String,
    @Assisted(NavigationConstants.CoinDetailsRouteIconArg) icon: String,
    @Assisted(NavigationConstants.CoinDetailsRouteNameArg) name: String
) : BaseViewModel<CoinDetailState>(coroutineScope) {

    override val state: MutableStateFlow<CoinDetailState> = MutableStateFlow(initialState)

    init {
        state.value = CoinDetailState.Uninitialized(CoinDetailState.CoinData(symbol, icon, name))
    }

    override fun initialize() {
        if (state.value !is CoinDetailState.Uninitialized) return

        scope.launch {
            state.value = CoinDetailState.Loading(state.value.coinData)
            try {
                val coinDetail = fetchCoinDetailHelper(state.value.coinData.symbol)
                state.value = CoinDetailState.Loaded(state.value.coinData, coinDetail)
            } catch (exception: Exception) {
                state.value = CoinDetailState.Error(state.value.coinData)
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
