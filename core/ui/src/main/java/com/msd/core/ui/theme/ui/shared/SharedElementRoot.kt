package com.msd.core.ui.theme.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
fun SharedElementRoot(children: @Composable () -> Unit) {
    val rootState = remember { SharedElementRootState() }

    Box {
        CompositionLocalProvider(LocalSharedElementRootStateAmbient provides rootState) {
            children()
            SharedElementOverlay()
        }
    }

    DisposableEffect(key1 = Unit) {
        onDispose { rootState.dispose() }
    }
}