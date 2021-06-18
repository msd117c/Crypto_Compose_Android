package com.msd117.cryptocompose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.BaseView

@Composable
fun ChangeIndicatorView(isPositive: Boolean, modifier: Modifier) {
    val color = if (isPositive) {
        Color.Green
    } else {
        Color.Red
    }
    Canvas(modifier = modifier) {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = size
        )
    }
}

@Composable
@Preview
fun ChangeIndicatorViewPreview() {
    BaseView {
        ChangeIndicatorView(true,
            Modifier
                .fillMaxHeight()
                .width(30.dp))
    }
}