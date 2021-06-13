package com.msd117.cryptocompose.theme

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

@Composable
fun CryptoComposeTheme(content: @Composable () -> Unit) {
    val darkColors = Colors(
        primary = Color.DarkGray,
        primaryVariant = Color.Black,
        secondary = Color.DarkGray,
        secondaryVariant = Color.Black,
        background = Color.DarkGray,
        surface = Color.Black,
        error = Color.Red,
        onPrimary = Color.White,
        onSecondary = Color.LightGray,
        onBackground = Color.White,
        onSurface = Color.White,
        onError = Color.White,
        isLight = false
    )

    val lightColors = Colors(
        primary = Color.LightGray,
        primaryVariant = Color.White,
        secondary = Color.LightGray,
        secondaryVariant = Color.White,
        background = Color.LightGray,
        surface = Color.White,
        error = Color.Red,
        onPrimary = Color.Black,
        onSecondary = Color.DarkGray,
        onBackground = Color.Black,
        onSurface = Color.Black,
        onError = Color.Black,
        isLight = true
    )

    MaterialTheme(
        colors = if (isSystemInDarkTheme()) darkColors else lightColors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun BaseView(content: @Composable () -> Unit) {
    CryptoComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

fun AppCompatActivity.setUi(content: @Composable () -> Unit) {
    setContent {
        BaseView {
            window.statusBarColor = MaterialTheme.colors.background.toArgb()
            window.navigationBarColor = MaterialTheme.colors.background.toArgb()
            content()
        }
    }
}