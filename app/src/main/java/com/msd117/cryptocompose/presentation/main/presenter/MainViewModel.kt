package com.msd117.cryptocompose.presentation.main.presenter

import androidx.lifecycle.ViewModel
import com.msd117.cryptocompose.domain.usecase.connection.IsConnectionAvailableUseCase
import com.msd117.cryptocompose.utils.getViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val isConnectionAvailableUseCase: IsConnectionAvailableUseCase
) : ViewModel() {

    private val scope = getViewModelScope(coroutineScope)
    private val state: MutableStateFlow<MainState> = MutableStateFlow(initialState)
    fun getState(): Flow<MainState> = state

    fun initialize() {
        if (state.value !is MainState.Uninitialized) return

        isConnectionAvailableUseCase().onEach { isConnected ->
            state.value = MainState.Loaded(isConnected = isConnected)
        }.launchIn(scope)
    }
}