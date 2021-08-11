package com.msd117.cryptocompose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.BaseView
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Compass(width: Dp) {
    Column(modifier = Modifier.width(width)) {
        Text(text = "Median")
        val height = width.div(2f)
        Canvas(modifier = Modifier.size(width, height)) {
            drawArc(
                Color.Black,
                180f,
                180f,
                false,
                Offset(width.times(.05f).toPx(), height.times(.1f).toPx()),
                Size(width.times(.9f).toPx(), width.times(.9f).toPx())
            )
            drawLine(
                Color.Blue,
                Offset(0f, height.toPx()),
                Offset(width.toPx(), height.toPx()),
                10f
            )
            drawNeedle(
                color = Color.Cyan,
                width = width,
                height = height,
                value = .5f
            )
            drawNeedle(
                color = Color.Green,
                width = width,
                height = height,
                value = 1f
            )
        }
    }
}

private fun DrawScope.drawNeedle(color: Color, width: Dp, height: Dp, value: Float) {
    val needleHeight = height.times(.96f)
    val angle = 180f.times(value)
    val objectiveHeightPercent = when {
        angle > 90f -> (height - needleHeight.times(sin((180 - angle).toRads()))).toPx()
        angle < 90f -> (height - needleHeight.times(sin(angle.toRads()))).toPx()
        else -> height.times(.04f).toPx()
    }
    val objectiveWidthPercent = when {
        angle > 90f -> (height + needleHeight.times(cos((180 - angle).toRads()))).toPx()
        angle < 90f -> (height - needleHeight.times(cos(angle.toRads()))).toPx()
        else -> width.times(.5f).toPx()
    }
    val objectiveOffset = Offset(objectiveWidthPercent, objectiveHeightPercent)
    drawLine(
        color,
        Offset(width.times(.5f).toPx(), height.toPx()),
        objectiveOffset,
        5f
    )
    drawCircle(color, 10f, objectiveOffset)
}

private fun Float.toRads(): Float = this.times(PI).div(180f).toFloat()

@Composable
@Preview
fun CompassPreview() {
    BaseView {
        Compass(200.dp)
    }
}