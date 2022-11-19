package com.msd.home.presenter

import androidx.annotation.StringRes
import com.msd.core.navigation.NavigationConstants.CategoriesRoute
import com.msd.core.navigation.NavigationConstants.LatestCoinsRoute
import com.msd.core.presentation.State
import com.msd.home.R

sealed class MainState : State {

    object Uninitialized : MainState()
    data class Loaded(
        val isConnected: Boolean,
        val items: List<MenuItem> = MenuItem.values().toList()
    ) : MainState() {

        enum class MenuItem(@StringRes val label: Int, val route: String) {
            LATEST_COINS(R.string.menu_item_latest_coins, LatestCoinsRoute),
            CATEGORIES(R.string.menu_item_categories, CategoriesRoute),
        }
    }
}

val initialState = MainState.Uninitialized
