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
    type: SharedElementType,
    modifier: Modifier,
    children: @Composable () -> Unit
) {
    val elementInfo = SharedElementInfo(tag, type)
    val rootState = LocalSharedElementsRootStateAmbient.current

    val visibility by animateFloatAsState(
        if (elementInfo.type == SharedElementType.To) {
            rootState.states[elementInfo.tag]?.let { sharedElementState ->
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
                    rootCoordinates = null,
                    layoutCoordinates = coordinates,
                    placeholder = children
                )
            }
            .then(Modifier.graphicsLayer(alpha = visibility))
    ) {
        children()
    }

    DisposableEffect(key1 = Unit) {
        rootState.registerElement(elementInfo)
        onDispose {
            rootState.disposeElement(elementInfo)
        }
    }
}

@Composable
fun SharedElementRoot(children: @Composable () -> Unit) {
    var animationState by remember { mutableStateOf(false) }
    val rootState =
        remember { SharedElementTransition({ animationState = true }, { animationState = false }) }

    Box {
        CompositionLocalProvider(LocalSharedElementsRootStateAmbient provides rootState) {
            children()
            if (animationState) {
                SharedElementOverlay()
            }
        }
    }
}

private val LocalSharedElementsRootStateAmbient =
    staticCompositionLocalOf<SharedElementTransition> {
        error("SharedElementsRoot not found. SharedElement must be hosted in SharedElementsRoot.")
    }

@Composable
fun SharedElementOverlay() {
    val rootState = LocalSharedElementsRootStateAmbient.current
    val states = remember { rootState.states }
    states.forEach { (tag, sharedElementState) ->
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

private class SharedElementTransition(
    private val readyListener: () -> Unit,
    private val finishedListener: () -> Unit
) {

    var startElementInfo: SharedElementInfo? = null
    var endElementInfo: SharedElementInfo? = null
    private var startElementPositioned = false

    val states = mutableStateMapOf<String, SharedElementState>()

    fun onElementPositioned(
        elementInfo: SharedElementInfo,
        rootCoordinates: LayoutCoordinates?,
        layoutCoordinates: LayoutCoordinates,
        placeholder: @Composable () -> Unit
    ) {
        if (startElementInfo == elementInfo) {
            startElementInfo = null
            startElementPositioned = true
            states[elementInfo.tag] = SharedElementState(
                state = mutableStateOf(SharedElementState.State.START),
                endElementState = mutableStateOf(SharedElementState.State.START),
                placeholder = placeholder,
                startStateProps = SharedElementSateProps(
                    position = calculateElementBoundsInRoot(
                        rootCoordinates,
                        layoutCoordinates
                    ).topLeft,
                    size = calculateElementSize(
                        calculateElementBoundsInRoot(
                            rootCoordinates,
                            layoutCoordinates
                        )
                    )
                ),
                endStateProps = SharedElementSateProps(position = Offset.Unspecified, size = 0f)
            )
            return
        }
        if (endElementInfo == elementInfo) {
            endElementInfo = null
            states[elementInfo.tag]?.let { sharedElementState ->
                states[elementInfo.tag] = sharedElementState.copy(
                    endStateProps = SharedElementSateProps(
                        position = calculateElementBoundsInRoot(
                            rootCoordinates,
                            layoutCoordinates
                        ).topLeft,
                        size = calculateElementSize(
                            calculateElementBoundsInRoot(
                                rootCoordinates,
                                layoutCoordinates
                            )
                        )
                    )
                )
            }
        }
        states[elementInfo.tag]?.let { sharedElementState ->
            if (
                sharedElementState.startStateProps.position != Offset.Unspecified &&
                sharedElementState.endStateProps.position != Offset.Unspecified
            ) {
                readyListener()
            }
        }
    }

    fun startTransition(tag: String) {
        states[tag]?.let { sharedElementState ->
            sharedElementState.state.value = SharedElementState.State.END
        }
    }

    fun finishTransition(tag: String) {
        states[tag]?.let { sharedElementState ->
            sharedElementState.endElementState.value = SharedElementState.State.END
        }
        finishedListener()
        states.remove(tag)
        startElementInfo = null
        endElementInfo = null
    }

    fun registerElement(elementInfo: SharedElementInfo) {
        when {
            startElementInfo == null && elementInfo.type == SharedElementType.From ->
                startElementInfo = elementInfo
            startElementPositioned && elementInfo.type == SharedElementType.To ->
                endElementInfo = elementInfo
        }
    }

    fun disposeElement(elementInfo: SharedElementInfo) {
        states[elementInfo.tag]?.let {
            if (elementInfo.type == SharedElementType.To && it.endStateProps.position != Offset.Unspecified) {
                states.remove(elementInfo.tag)
            }
        }
    }

    private fun calculateElementBoundsInRoot(
        rootCoordinates: LayoutCoordinates?,
        elementCoordinates: LayoutCoordinates
    ): Rect {
        return rootCoordinates?.localBoundingBoxOf(elementCoordinates)
            ?: elementCoordinates.boundsInRoot()
    }

    private fun calculateElementSize(boundingBox: Rect): Float {
        return boundingBox.right - boundingBox.left
    }
}

data class SharedElementInfo(val tag: String, val type: SharedElementType)

enum class SharedElementType {
    From, To
}

data class SharedElementState(
    val state: MutableState<State>,
    val endElementState: MutableState<State>,
    val placeholder: @Composable () -> Unit,
    val startStateProps: SharedElementSateProps,
    val endStateProps: SharedElementSateProps
) {

    enum class State {
        START, END
    }
}

data class SharedElementSateProps(
    val position: Offset,
    val size: Float
)