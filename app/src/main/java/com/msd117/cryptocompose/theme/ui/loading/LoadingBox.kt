package com.msd117.cryptocompose.theme.ui.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.theme.paddingM
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadingBox(width: Dp, height: Dp, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(width = width, height = height)
            .shimmer()
            .background(color = Color.Gray)
    )
}

@Composable
fun LoadingBox(size: Dp, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(size = size)
            .shimmer()
            .background(color = Color.Gray)
    )
}

@Composable
fun LoadingCircle(size: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(size = size)
            .clip(CircleShape)
            .shimmer()
            .background(color = Color.Gray)
    )
}

@Composable
@Preview
fun LoadingBoxPreview() {
    BaseView {
        LoadingBox(width = 70.dp, height = 70.dp, modifier = Modifier.padding(all = paddingM))
        LoadingBox(size = 70.dp, modifier = Modifier.padding(all = paddingM))
    }
}