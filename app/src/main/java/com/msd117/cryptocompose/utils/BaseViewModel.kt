package com.msd117.cryptocompose.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<S : State>(coroutineScope: CoroutineScope?) : ViewModel() {

    protected val scope = getViewModelScope(coroutineScope)

    protected abstract val state: MutableStateFlow<S>
    fun getState(): Flow<S> = state
}