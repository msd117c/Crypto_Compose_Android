package com.msd117.cryptocompose.presentation.main.presenter

import com.msd117.cryptocompose.presentation.main.ui.MenuItem

data class MainState(
    val isConnected: Boolean,
    val menuItem: MenuItem?
)

val initialState = MainState(isConnected = true, menuItem = null)