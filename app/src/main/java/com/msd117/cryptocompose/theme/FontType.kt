package com.msd117.cryptocompose.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.msd117.cryptocompose.R

val typography = Typography(defaultFontFamily = appFontFamily())

private fun appFontFamily() = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.nunito_regular,
            weight = FontWeight.W900,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.nunito_italic,
            weight = FontWeight.W900,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.nunito_bold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        )
    )
)
