package com.msd117.cryptocompose.theme.ui.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SmallBodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSecondary,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}
