package com.msd117.cryptocompose.presentation.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.theme.BaseView

@Composable
fun SplashView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_coinmarket_logo),
            contentDescription = LocalContext.current.getString(R.string.splash_image_content_description),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun SplashViewPreview() {
    BaseView {
        SplashView()
    }
}