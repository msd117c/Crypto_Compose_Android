package com.msd.core.presentation

import androidx.lifecycle.ViewModel
import com.msd.core.navigation.NavigationEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State>(
    coroutineScope: CoroutineScope?
) : ViewModel() {

    protected val scope = getViewModelScope(coroutineScope)

    protected abstract val state: MutableStateFlow<S>
    fun getState(): Flow<S> = state
    private val navigationEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.Idle)
    fun getNavigationEvent(): Flow<NavigationEvent> = navigationEvent

    fun navigate(route: NavigationEvent.Route) {
        scope.launch { navigationEvent.emit(route) }
    }

    fun popBackStack() {
        scope.launch { navigationEvent.emit(NavigationEvent.PopBackStack) }
    }

    fun cleanNavigation() {
        scope.launch { navigationEvent.emit(NavigationEvent.Idle) }
    }

    abstract fun initialize()
}
