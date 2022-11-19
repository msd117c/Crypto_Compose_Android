package com.msd117.cryptocompose.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msd.core.navigation.NavigationConstants.CategoriesRoute
import com.msd.core.navigation.NavigationConstants.CoinDetailsRoute
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteIconArg
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteNameArg
import com.msd.core.navigation.NavigationConstants.CoinDetailsRouteSymbolArg
import com.msd.core.navigation.NavigationConstants.LatestCoinsRoute
import com.msd.core.navigation.NavigationConstants.MainRoute
import com.msd.core.navigation.NavigationEvent
import com.msd.core.presentation.BaseViewModel
import com.msd.core.presentation.State
import com.msd.core.ui.theme.setUi
import com.msd.core.ui.theme.ui.shared.SharedElementRoot
import com.msd.home.presenter.MainViewModel
import com.msd.home.ui.MainView
import com.msd.latest_coins.detail.presenter.CoinDetailViewModel
import com.msd.latest_coins.detail.ui.CoinDetailView
import com.msd.latest_coins.list.presenter.LatestCoinsViewModel
import com.msd.latest_coins.list.ui.LatestCoinsView
import com.msd117.cryptocompose.categories.presenter.CategoriesViewModel
import com.msd117.cryptocompose.categories.ui.CategoriesView
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
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

    @ExperimentalTransitionApi
    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @Composable
    fun coinDetailViewModel(symbol: String, icon: String, name: String): CoinDetailViewModel {
        val factory = EntryPointAccessors.fromActivity(
            LocalContext.current as Activity,
            ViewModelFactoryProvider::class.java
        ).coinDetailViewModelFactory()

        return viewModel(factory = CoinDetailViewModel.provideFactory(factory, symbol, icon, name))
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

            LaunchedEffect(Unit) { viewModel.initialize() }
        }
    }
}
