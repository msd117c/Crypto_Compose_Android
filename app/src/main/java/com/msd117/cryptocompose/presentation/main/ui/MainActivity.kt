package com.msd117.cryptocompose.presentation.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msd117.cryptocompose.presentation.latest.ui.view.LatestCoinsView
import com.msd117.cryptocompose.presentation.splash.ui.SplashView
import com.msd117.cryptocompose.theme.setUi
import com.msd117.cryptocompose.utils.NavigationConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavigationConstants.SplashRoute
            ) {
                composable(NavigationConstants.SplashRoute) {
                    SplashView(navController = navController)
                }
                composable(NavigationConstants.MainRoute) {
                    MainView(mainViewModel = hiltViewModel(), navController = navController)
                }
                composable(NavigationConstants.LatestCoinsRoute) {
                    LatestCoinsView(viewModel = hiltViewModel(), navController = navController)
                }
            }
        }
    }
}