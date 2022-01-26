package com.msd117.cryptocompose.theme.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset

sealed class SharedElementState {
    object Empty : SharedElementState()
    data class SourceLoaded(val startStateProps: SharedElementSateProps) : SharedElementState()

    data class DestinationLoaded(
        val startStateProps: SharedElementSateProps,
        val endStateProps: SharedElementSateProps,
        val placeholder: @Composable () -> Unit
    ) : SharedElementState()

    data class AnimationReady(
        val state: MutableState<State> = mutableStateOf(State.START),
        val endElementState: MutableState<State> = mutableStateOf(State.START),
        val startStateProps: SharedElementSateProps,
        val endStateProps: SharedElementSateProps,
        val placeholder: @Composable () -> Unit
    ) : SharedElementState()

    enum class State {
        START, END
    }

    data class SharedElementSateProps(
        val position: Offset,
        val size: Float
    )
}
