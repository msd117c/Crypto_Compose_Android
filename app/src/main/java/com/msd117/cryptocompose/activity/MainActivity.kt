package com.msd117.cryptocompose.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msd117.cryptocompose.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.detail.ui.CoinDetailView
import com.msd117.cryptocompose.detail.ui.coinDetailViewModel
import com.msd117.cryptocompose.latest.ui.LatestCoinsView
import com.msd117.cryptocompose.main.ui.MainView
import com.msd117.cryptocompose.theme.setUi
import com.msd117.cryptocompose.theme.ui.shared.SharedElementRoot
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRoute
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteIconArg
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteNameArg
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteSymbolArg
import com.msd117.cryptocompose.utils.NavigationConstants.LatestCoinsRoute
import com.msd117.cryptocompose.utils.NavigationConstants.MainRoute
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

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
                    startDestination = MainRoute
                ) {
                    composable(MainRoute) {
                        MainView(viewModel = hiltViewModel(), navController = navController)
                    }
                    composable(LatestCoinsRoute) {
                        LatestCoinsView(viewModel = hiltViewModel(), navController = navController)
                    }
                    composable(
                        CoinDetailsRoute,
                        arguments = listOf(
                            navArgument(CoinDetailsRouteSymbolArg) { type = NavType.StringType },
                            navArgument(CoinDetailsRouteIconArg) { type = NavType.StringType },
                            navArgument(CoinDetailsRouteNameArg) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        with(backStackEntry.arguments) {
                            val symbol = this?.getString(CoinDetailsRouteSymbolArg).orEmpty()
                            val icon = this?.getString(CoinDetailsRouteIconArg).orEmpty()
                            val name = this?.getString(CoinDetailsRouteNameArg).orEmpty()
                            val viewModel = coinDetailViewModel(symbol, icon, name)

                            CoinDetailView(viewModel = viewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
