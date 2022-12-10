package com.msd.latest_coins.detail.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteIconArg
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteNameArg
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteSymbolArg
import com.msd.core.presentation.BaseViewModel
import com.msd.latest_coins.detail.presenter.helper.FetchCoinDetailHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel @AssistedInject constructor(
    private val fetchCoinDetailHelper: FetchCoinDetailHelper,
    @Assisted(CoinDetailsRouteSymbolArg) symbol: String,
    @Assisted(CoinDetailsRouteIconArg) icon: String,
    @Assisted(CoinDetailsRouteNameArg) name: String
) : BaseViewModel<CoinDetailState>() {

    override val state: MutableStateFlow<CoinDetailState> = MutableStateFlow(initialState)

    init {
        state.value = CoinDetailState.Uninitialized(CoinDetailState.CoinData(symbol, icon, name))
    }

    override fun initialize() {
        if (state.value !is CoinDetailState.Uninitialized) return

        viewModelScope.launch {
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
            @Assisted(CoinDetailsRouteSymbolArg) symbol: String,
            @Assisted(CoinDetailsRouteIconArg) icon: String,
            @Assisted(CoinDetailsRouteNameArg) name: String
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
