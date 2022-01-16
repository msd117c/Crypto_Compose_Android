package com.msd117.cryptocompose.presentation.main.presenter

sealed class MainState {

    object Uninitialized : MainState()
    data class Loaded(val isConnected: Boolean) : MainState()
}

val initialState = MainState.Uninitialized