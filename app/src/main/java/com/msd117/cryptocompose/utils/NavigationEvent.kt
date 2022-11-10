package com.msd117.cryptocompose.utils

sealed class NavigationEvent {

    object Idle : NavigationEvent()
    object PopBackStack : NavigationEvent()
    data class Route(val routeId: String) : NavigationEvent()
}
