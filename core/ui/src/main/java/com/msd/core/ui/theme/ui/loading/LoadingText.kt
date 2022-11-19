package com.msd.core.ui.theme.ui.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msd.core.ui.theme.BaseView
import com.msd.core.ui.theme.Padding.paddingM
import com.valentinilk.shimmer.shimmer

enum class Height(val dp: Dp) {
    Small(12.dp),
    Medium(18.dp),
    Large(24.dp)
}

@Composable
fun LoadingText(width: Dp, height: Height, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = width, height = height.dp)
            .shimmer()
            .background(color = Color.Gray)
    )
}

@Composable
fun LoadingText(height: Height, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = height.dp)
            .shimmer()
            .background(color = Color.Gray)
    )
}

@Composable
@Preview
fun LoadingTextPreview() {
    BaseView {
        LoadingText(
            width = 70.dp,
            height = Height.Medium,
            modifier = Modifier.padding(all = paddingM)
        )
    }
}