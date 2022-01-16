package com.msd117.cryptocompose.presentation.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.presentation.detail.ui.view.CoinDetailView
import com.msd117.cryptocompose.presentation.detail.ui.view.coinDetailViewModel
import com.msd117.cryptocompose.presentation.latest.ui.LatestCoinsView
import com.msd117.cryptocompose.presentation.splash.ui.SplashView
import com.msd117.cryptocompose.theme.setUi
import com.msd117.cryptocompose.utils.NavigationConstants
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun coinDetailViewModelFactory(): CoinDetailViewModel.Factory
    }

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
                    MainView(viewModel = hiltViewModel(), navController = navController)
                }
                composable(NavigationConstants.LatestCoinsRoute) {
                    LatestCoinsView(viewModel = hiltViewModel(), navController = navController)
                }
                composable(
                    NavigationConstants.CoinDetailsRoute,
                    arguments = listOf(navArgument(NavigationConstants.CoinDetailsRouteSymbolArg) {
                        type = NavType.StringType
                    })
                ) { backStackEntry ->
                    val symbol = backStackEntry.arguments?.getString(
                        NavigationConstants.CoinDetailsRouteSymbolArg
                    ).orEmpty()
                    val viewModel = coinDetailViewModel(symbol = symbol)
                    CoinDetailView(viewModel = viewModel)
                }
            }
        }
    }
}