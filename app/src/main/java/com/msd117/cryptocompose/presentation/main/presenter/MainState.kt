package com.msd117.cryptocompose.presentation.main.presenter

import com.msd117.cryptocompose.utils.State

sealed class MainState : State {

    object Uninitialized : MainState()
    data class Loaded(val isConnected: Boolean) : MainState()
}

val initialState = MainState.Uninitialized