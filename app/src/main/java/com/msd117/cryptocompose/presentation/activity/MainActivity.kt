package com.msd117.cryptocompose.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.presentation.detail.ui.CoinDetailView
import com.msd117.cryptocompose.presentation.detail.ui.coinDetailViewModel
import com.msd117.cryptocompose.presentation.latest.ui.LatestCoinsView
import com.msd117.cryptocompose.presentation.main.ui.MainView
import com.msd117.cryptocompose.presentation.splash.ui.SplashView
import com.msd117.cryptocompose.theme.setUi
import com.msd117.cryptocompose.theme.ui.shared.SharedElementRoot
import com.msd117.cryptocompose.utils.NavigationConstants
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@ExperimentalUnitApi
@ExperimentalComposeUiApi
@ExperimentalTransitionApi
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
            SharedElementRoot {
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
                        arguments = listOf(
                            navArgument(NavigationConstants.CoinDetailsRouteSymbolArg) {
                                type = NavType.StringType
                            },
                            navArgument(NavigationConstants.CoinDetailsRouteIconArg) {
                                type = NavType.StringType
                            },
                            navArgument(NavigationConstants.CoinDetailsRouteNameArg) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val symbol = backStackEntry.arguments?.getString(
                            NavigationConstants.CoinDetailsRouteSymbolArg
                        ).orEmpty()
                        val icon = backStackEntry.arguments?.getString(
                            NavigationConstants.CoinDetailsRouteIconArg
                        ).orEmpty()
                        val name = backStackEntry.arguments?.getString(
                            NavigationConstants.CoinDetailsRouteNameArg
                        ).orEmpty()
                        val viewModel = coinDetailViewModel(symbol, icon, name)
                        CoinDetailView(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }
    }
}