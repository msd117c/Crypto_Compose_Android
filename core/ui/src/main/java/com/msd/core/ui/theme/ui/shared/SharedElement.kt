package com.msd.core.ui.theme.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun SharedElement(
    tagProvider: () -> String,
    type: SharedElementInfo.SharedElementType,
    modifier: Modifier,
    children: @Composable () -> Unit
) {
    val elementInfo = SharedElementInfo(tagProvider(), type)
    val rootState = LocalSharedElementRootStateAmbient.current

    val isVisible = remember { mutableStateOf(1f) }
    if (elementInfo.type == SharedElementInfo.SharedElementType.To) {
        rootState.setEndElementVisibilityListener(tagProvider()) { isVisible.value = it }
    }

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                rootState.onElementPositioned(
                    elementInfo = elementInfo,
                    layoutCoordinates = coordinates,
                    placeholder = children
                )
            }
            .then(Modifier.graphicsLayer(alpha = isVisible.value))
    ) {
        children()
    }

    DisposableEffect(key1 = Unit) {
        rootState.onElementRegistered(elementInfo)
        onDispose { rootState.onElementDisposed(elementInfo) }
    }
}
