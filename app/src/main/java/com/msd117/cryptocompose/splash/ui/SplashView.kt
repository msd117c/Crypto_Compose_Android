package com.msd117.cryptocompose.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.utils.NavigationConstants
import kotlinx.coroutines.delay

@Composable
fun SplashView(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        navController.navigate(NavigationConstants.MainRoute) {
            popUpTo(NavigationConstants.SplashRoute) { inclusive = true }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_coinmarket_logo),
            contentDescription = stringResource(R.string.splash_image_content_description),
            modifier = Modifier.align(Alignment.Center),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary)
        )
    }
}

@Preview
@Composable
fun SplashViewPreview() {
    BaseView {
        //SplashView()
    }
}
