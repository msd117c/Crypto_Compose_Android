package com.msd117.cryptocompose.main.presenter

import androidx.annotation.StringRes
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.utils.NavigationConstants.CategoriesRoute
import com.msd117.cryptocompose.utils.NavigationConstants.LatestCoinsRoute
import com.msd117.cryptocompose.utils.State

sealed class MainState : State {

    object Uninitialized : MainState()
    data class Loaded(
        val isConnected: Boolean,
        val items: List<MenuItem> = MenuItem.values().toList()
    ) : MainState() {

        enum class MenuItem(@StringRes val label: Int, val route: String) {
            LATEST_COINS(R.string.latest_coins_title, LatestCoinsRoute),
            CATEGORIES(R.string.menu_item_categories, CategoriesRoute),
        }
    }
}

val initialState = MainState.Uninitialized
