package com.msd117.cryptocompose.theme.ui.shared

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset

private const val durationMillis = 500

@Composable
fun SharedElementOverlay() {
    val rootState = LocalSharedElementRootStateAmbient.current
    val states = remember { rootState.getStates() }
    states.forEach { (tag, sharedElementState) ->
        if (sharedElementState is SharedElementState.AnimationReady) {
            val state by remember { sharedElementState.state }

            val animationFinished: (Offset) -> Unit = {
                rootState.finishTransition(tag)
            }

            val position by animateOffsetAsState(
                if (state == SharedElementState.State.START) {
                    sharedElementState.startStateProps.position
                } else {
                    sharedElementState.endStateProps.position
                },
                animationSpec = tween(durationMillis),
                finishedListener = animationFinished
            )
            val size by animateDpAsState(
                with(LocalDensity.current) {
                    if (state == SharedElementState.State.START) {
                        sharedElementState.startStateProps.size.toDp()
                    } else {
                        sharedElementState.endStateProps.size.toDp()
                    }
                },
                animationSpec = tween(durationMillis)
            )

            Box(
                modifier = Modifier
                    .offset { IntOffset(x = position.x.toInt(), y = position.y.toInt()) }
                    .size(size)
            ) {
                sharedElementState.placeholder()
            }

            rootState.startTransition(tag)
        }
    }
}