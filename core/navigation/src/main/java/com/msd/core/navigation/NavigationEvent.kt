package com.msd.core.navigation

sealed class NavigationEvent {

    object Idle : NavigationEvent()
    object PopBackStack : NavigationEvent()
    data class Route(val routeId: String) : NavigationEvent()
}
