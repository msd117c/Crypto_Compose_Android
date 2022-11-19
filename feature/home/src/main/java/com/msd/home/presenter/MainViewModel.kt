package com.msd.home.presenter

import com.msd.core.presentation.BaseViewModel
import com.msd.domain.network_capabilities.IsConnectionAvailableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val isConnectionAvailableUseCase: IsConnectionAvailableUseCase
) : BaseViewModel<MainState>(coroutineScope) {

    override val state: MutableStateFlow<MainState> = MutableStateFlow(initialState)

    override fun initialize() {
        if (state.value !is MainState.Uninitialized) return

        isConnectionAvailableUseCase().onEach { isConnected ->
            state.value = MainState.Loaded(isConnected = isConnected)
        }.launchIn(scope)
    }
}