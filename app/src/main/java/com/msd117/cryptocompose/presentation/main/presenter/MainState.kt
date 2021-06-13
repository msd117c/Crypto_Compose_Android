package com.msd117.cryptocompose.presentation.main.presenter

data class MainState(
    val connection: Boolean
)

val initialState = MainState(connection = false)