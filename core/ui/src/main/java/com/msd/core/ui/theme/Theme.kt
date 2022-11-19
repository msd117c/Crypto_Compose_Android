package com.msd.core.ui.theme

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
        primaryVariant = Color.DarkGray,
        secondary = Color.DarkGray,
        secondaryVariant = Color.DarkGray,
        background = Color.Black,
        surface = Color.DarkGray,
        error = redDark,
        onPrimary = Color.White,
        onSecondary = Color.LightGray,
        onBackground = Color.White,
        onSurface = Color.White,
        onError = Color.LightGray,
        isLight = false
    )

    val lightColors = Colors(
        primary = Color.White,
        primaryVariant = Color.White,
        secondary = Color.White,
        secondaryVariant = Color.White,
        background = Color.White,
        surface = Color.White,
        error = redLight,
        onPrimary = Color.DarkGray,
        onSecondary = Color.LightGray,
        onBackground = Color.DarkGray,
        onSurface = Color.LightGray,
        onError = Color.White,
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