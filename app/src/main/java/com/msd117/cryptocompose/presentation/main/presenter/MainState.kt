package com.msd117.cryptocompose.presentation.main.presenter

data class MainState(
    val isConnected: Boolean
)

val initialState = MainState(isConnected = true)