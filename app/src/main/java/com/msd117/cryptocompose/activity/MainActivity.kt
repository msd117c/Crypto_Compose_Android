package com.msd117.cryptocompose.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msd117.cryptocompose.categories.presenter.CategoriesViewModel
import com.msd117.cryptocompose.categories.ui.CategoriesView
import com.msd117.cryptocompose.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.detail.ui.CoinDetailView
import com.msd117.cryptocompose.detail.ui.coinDetailViewModel
import com.msd117.cryptocompose.latest.presenter.LatestCoinsViewModel
import com.msd117.cryptocompose.latest.ui.LatestCoinsView
import com.msd117.cryptocompose.main.presenter.MainViewModel
import com.msd117.cryptocompose.main.ui.MainView
import com.msd117.cryptocompose.theme.setUi
import com.msd117.cryptocompose.theme.ui.shared.SharedElementRoot
import com.msd117.cryptocompose.utils.BaseViewModel
import com.msd117.cryptocompose.utils.NavigationConstants.CategoriesRoute
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRoute
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteIconArg
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteNameArg
import com.msd117.cryptocompose.utils.NavigationConstants.CoinDetailsRouteSymbolArg
import com.msd117.cryptocompose.utils.NavigationConstants.LatestCoinsRoute
import com.msd117.cryptocompose.utils.NavigationConstants.MainRoute
import com.msd117.cryptocompose.utils.NavigationEvent
import com.msd117.cryptocompose.utils.State
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
                    composable(
                        this,
                        MainRoute,
                        emptyList(),
                        navController,
                        { hiltViewModel<MainViewModel>() },
                        { viewModel -> MainView(viewModel = viewModel) }
                    )
                    composable(
                        this,
                        LatestCoinsRoute,
                        emptyList(),
                        navController,
                        { hiltViewModel<LatestCoinsViewModel>() },
                        { viewModel ->
                            LatestCoinsView(viewModel = viewModel)
                        }
                    )
                    composable(
                        this,
                        CoinDetailsRoute,
                        listOf(
                            navArgument(CoinDetailsRouteSymbolArg) { type = NavType.StringType },
                            navArgument(CoinDetailsRouteIconArg) { type = NavType.StringType },
                            navArgument(CoinDetailsRouteNameArg) { type = NavType.StringType }
                        ),
                        navController,
                        {
                            with(navController.currentBackStackEntry?.arguments) {
                                val symbol = this?.getString(CoinDetailsRouteSymbolArg).orEmpty()
                                val icon = this?.getString(CoinDetailsRouteIconArg).orEmpty()
                                val name = this?.getString(CoinDetailsRouteNameArg).orEmpty()
                                coinDetailViewModel(symbol, icon, name)
                            }
                        },
                        { viewModel -> CoinDetailView(viewModel = viewModel) }
                    )
                    composable(
                        this,
                        CategoriesRoute,
                        emptyList(),
                        navController,
                        { hiltViewModel<CategoriesViewModel>() },
                        { viewModel -> CategoriesView(viewModel = viewModel) }
                    )
                }
            }
        }
    }

    fun <S : State, V : BaseViewModel<S>> composable(
        builder: NavGraphBuilder,
        route: String,
        arguments: List<NamedNavArgument>,
        navController: NavHostController,
        viewModelProvider: @Composable () -> V,
        content: @Composable (V) -> Unit
    ) {
        builder.composable(route, arguments) {
            val viewModel = viewModelProvider()

            val navigationEvent by viewModel.getNavigationEvent()
                .collectAsState(initial = NavigationEvent.Idle)

            LaunchedEffect(navigationEvent) {
                when (navigationEvent) {
                    is NavigationEvent.Idle -> Unit
                    is NavigationEvent.PopBackStack -> {
                        navController.popBackStack()
                        viewModel.cleanNavigation()
                    }
                    is NavigationEvent.Route -> {
                        navController.navigate((navigationEvent as NavigationEvent.Route).routeId)
                        viewModel.cleanNavigation()
                    }
                }
            }

            content(viewModel)

            viewModel.initialize()
        }
    }
}
