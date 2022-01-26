package com.msd117.cryptocompose.theme.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot

val LocalSharedElementRootStateAmbient = staticCompositionLocalOf<SharedElementRootState> {
    error("SharedElementRoot not found. SharedElement must be hosted in SharedElementRoot.")
}

class SharedElementRootState {

    private val states = mutableStateMapOf<String, SharedElementState>()
    private val endElementVisibilityListeners = mutableMapOf<String, (Float) -> Unit>()

    fun getStates(): SnapshotStateMap<String, SharedElementState> = states

    fun setEndElementVisibilityListener(tag: String, listener: (Float) -> Unit) {
        endElementVisibilityListeners[tag] = listener
    }

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
                    val elementProps = layoutCoordinates.buildElementProps()
                    when {
                        elementInfo.type == SharedElementInfo.SharedElementType.From &&
                                (sharedElementState as? SharedElementState.SourceLoaded)?.startStateProps != elementProps -> {
                            states[tag] = SharedElementState.SourceLoaded(
                                startStateProps = elementProps
                            )
                        }
                        elementInfo.type == SharedElementInfo.SharedElementType.To &&
                                sharedElementState is SharedElementState.SourceLoaded -> {
                            states[tag] = SharedElementState.DestinationLoaded(
                                startStateProps = sharedElementState.startStateProps,
                                endStateProps = elementProps,
                                placeholder = placeholder
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
                endElementVisibilityListeners[tag]?.invoke(0f)
                sharedElementState.state.value = SharedElementState.State.END
            }
        }
    }

    fun finishTransition(tag: String) {
        endElementVisibilityListeners[tag]?.invoke(1f)
        states.remove(tag)
        endElementVisibilityListeners.remove(tag)
    }

    fun onElementRegistered(elementInfo: SharedElementInfo) {
        if (elementInfo.type == SharedElementInfo.SharedElementType.From && states[elementInfo.tag] == null) {
            states[elementInfo.tag] = SharedElementState.Empty
        }
    }

    fun onElementDisposed(elementInfo: SharedElementInfo) {
        elementInfo.tag.withState { sharedElementState ->
            when (elementInfo.type) {
                SharedElementInfo.SharedElementType.From -> {
                    if (
                        sharedElementState is SharedElementState.SourceLoaded ||
                        sharedElementState is SharedElementState.Empty
                    ) {
                        states.remove(elementInfo.tag)
                    }
                }
                SharedElementInfo.SharedElementType.To -> {
                    if (
                        sharedElementState is SharedElementState.AnimationReady ||
                        sharedElementState is SharedElementState.DestinationLoaded
                    ) {
                        states.remove(elementInfo.tag)
                    }
                }
            }
        }
    }

    fun dispose() {
        states.clear()
    }

    private fun String.withState(block: (SharedElementState) -> Unit) {
        states[this]?.let { sharedElementState -> block(sharedElementState) }
    }

    private fun calculateElementSize(boundingBox: Rect): Float {
        return boundingBox.right - boundingBox.left
    }
}