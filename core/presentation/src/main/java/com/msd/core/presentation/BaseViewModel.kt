package com.msd.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msd.core.navigation.NavigationEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State> : ViewModel() {

    protected abstract val state: MutableStateFlow<S>
    fun getState(): Flow<S> = state
    private val navigationEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.Idle)
    fun getNavigationEvent(): Flow<NavigationEvent> = navigationEvent

    fun navigate(route: NavigationEvent.Route) {
        viewModelScope.launch { navigationEvent.emit(route) }
    }

    fun popBackStack() {
        viewModelScope.launch { navigationEvent.emit(NavigationEvent.PopBackStack) }
    }

    fun cleanNavigation() {
        viewModelScope.launch { navigationEvent.emit(NavigationEvent.Idle) }
    }

    open fun initialize() {
        if (!state.value.isUninitialized()) return
    }
}
