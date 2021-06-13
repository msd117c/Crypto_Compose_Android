package com.msd117.cryptocompose.presentation.main.presenter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val state: MutableStateFlow<MainState?> = MutableStateFlow(null)
    fun getState(): Flow<MainState> = state.filterNotNull()
}