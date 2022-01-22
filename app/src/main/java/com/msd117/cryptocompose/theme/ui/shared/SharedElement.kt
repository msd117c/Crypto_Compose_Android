package com.msd117.cryptocompose.theme.ui.shared

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity

@Composable
fun SharedElement(
    tag: String,
    type: SharedElementInfo.SharedElementType,
    modifier: Modifier,
    children: @Composable () -> Unit
) {
    val elementInfo = SharedElementInfo(tag, type)
    val rootState = LocalSharedElementsRootStateAmbient.current

    val visibility by animateFloatAsState(
        if (elementInfo.type == SharedElementInfo.SharedElementType.To) {
            val states = remember { rootState.states }
            (states[elementInfo.tag] as? SharedElementState.AnimationReady)?.let { sharedElementState ->
                val state by remember { sharedElementState.endElementState }
                if (state == SharedElementState.State.END) {
                    1f
                } else {
                    0f
                }
            } ?: 1f
        } else 1f
    )

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                rootState.onElementPositioned(
                    elementInfo = elementInfo,
                    layoutCoordinates = coordinates,
                    placeholder = children
                )
            }
            .then(Modifier.graphicsLayer(alpha = visibility))
    ) {
        children()
    }

    DisposableEffect(key1 = Unit) {
        rootState.onElementRegistered(elementInfo)
        onDispose { }
    }
}

@Composable
fun SharedElementRoot(children: @Composable () -> Unit) {
    val rootState = remember { SharedElementRootState() }

    Box {
        CompositionLocalProvider(LocalSharedElementsRootStateAmbient provides rootState) {
            children()
            SharedElementOverlay()
        }
    }
}

@Composable
fun SharedElementOverlay() {
    val rootState = LocalSharedElementsRootStateAmbient.current
    val states = remember { rootState.states }
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
                finishedListener = animationFinished
            )
            val size by animateDpAsState(
                with(LocalDensity.current) {
                    if (state == SharedElementState.State.START) {
                        sharedElementState.startStateProps.size.toDp()
                    } else {
                        sharedElementState.endStateProps.size.toDp()
                    }
                }
            )
            Box(
                modifier = Modifier
                    .offset(
                        x = with(LocalDensity.current) { position.x.toDp() },
                        y = with(LocalDensity.current) { position.y.toDp() }
                    )
                    .size(size)
            ) {
                sharedElementState.placeholder()
            }
            rootState.startTransition(tag)
        }
    }
}

private val LocalSharedElementsRootStateAmbient =
    staticCompositionLocalOf<SharedElementRootState> {
        error("SharedElementsRoot not found. SharedElement must be hosted in SharedElementsRoot.")
    }

private class SharedElementRootState {

    val states = mutableStateMapOf<String, SharedElementState>()

    fun onElementPositioned(
        elementInfo: SharedElementInfo,
        layoutCoordinates: LayoutCoordinates,
        placeholder: @Composable () -> Unit
    ) {
        val tag = elementInfo.tag

        tag.withState { sharedElementState ->
            when (sharedElementState) {
                is SharedElementState.DestinationLoaded -> {
                    states[tag] = SharedElementState.AnimationReady(
                        startStateProps = sharedElementState.startStateProps,
                        endStateProps = sharedElementState.endStateProps,
                        placeholder = sharedElementState.placeholder
                    )
                }
                is SharedElementState.AnimationReady -> Unit
                else -> {
                    when {
                        elementInfo.type == SharedElementInfo.SharedElementType.From &&
                                sharedElementState is SharedElementState.Empty -> {
                            states[tag] = SharedElementState.SourceLoaded(
                                startStateProps = layoutCoordinates.buildElementProps(),
                                placeholder = placeholder
                            )
                        }
                        elementInfo.type == SharedElementInfo.SharedElementType.To &&
                                sharedElementState is SharedElementState.SourceLoaded -> {
                            states[tag] = SharedElementState.DestinationLoaded(
                                startStateProps = sharedElementState.startStateProps,
                                endStateProps = layoutCoordinates.buildElementProps(),
                                placeholder = sharedElementState.placeholder
                            )
                        }
                    }
                }
            }
        }
    }

    private fun LayoutCoordinates.buildElementProps(): SharedElementState.SharedElementSateProps {
        return SharedElementState.SharedElementSateProps(
            position = boundsInRoot().topLeft,
            size = calculateElementSize(boundsInRoot())
        )
    }

    fun startTransition(tag: String) {
        tag.withState { sharedElementState ->
            if (sharedElementState is SharedElementState.AnimationReady &&
                sharedElementState.state.value == SharedElementState.State.START
            ) {
                sharedElementState.state.value = SharedElementState.State.END
            }
        }
    }

    fun finishTransition(tag: String) {
        tag.withState { sharedElementState ->
            (sharedElementState as? SharedElementState.AnimationReady)?.endElementState?.value =
                SharedElementState.State.END
            states.remove(tag)
        }
    }

    fun onElementRegistered(elementInfo: SharedElementInfo) {
        if (elementInfo.type == SharedElementInfo.SharedElementType.From) {
            states[elementInfo.tag] = SharedElementState.Empty
        }
    }

    private fun String.withState(block: (SharedElementState) -> Unit) {
        states[this]?.let { sharedElementState -> block(sharedElementState) }
    }

    private fun calculateElementSize(boundingBox: Rect): Float {
        return boundingBox.right - boundingBox.left
    }
}

data class SharedElementInfo(val tag: String, val type: SharedElementType) {

    enum class SharedElementType { From, To }
}

sealed class SharedElementState {
    object Empty : SharedElementState()
    data class SourceLoaded(
        val startStateProps: SharedElementSateProps,
        val placeholder: @Composable () -> Unit
    ) : SharedElementState()

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
