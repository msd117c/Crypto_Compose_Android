package com.msd117.cryptocompose.main.ui

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.main.presenter.MainState
import com.msd117.cryptocompose.main.presenter.MainViewModel
import com.msd117.cryptocompose.main.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.theme.Padding.paddingL
import com.msd117.cryptocompose.theme.Padding.paddingM
import com.msd117.cryptocompose.theme.Padding.paddingS
import com.msd117.cryptocompose.theme.Size.sizeS
import com.msd117.cryptocompose.theme.ui.widget.BodyText
import com.msd117.cryptocompose.utils.NavigationConstants.CategoriesRoute
import com.msd117.cryptocompose.utils.NavigationConstants.LatestCoinsRoute

@Composable
fun MainView(viewModel: MainViewModel, navController: NavController) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)
    viewModel.initialize()

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is MainState.Uninitialized -> Unit
            is MainState.Loaded -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        MainMenuItem(
                            label = R.string.menu_item_coins,
                            isConnected = state.isConnected,
                            onClicked = { navController.navigate(LatestCoinsRoute) }
                        )
                        MainMenuItem(
                            label = R.string.menu_item_categories,
                            isConnected = state.isConnected,
                            onClicked = { navController.navigate(CategoriesRoute) }
                        )
                        MainMenuItem(
                            label = R.string.menu_item_markets,
                            isConnected = state.isConnected,
                            onClicked = {}
                        )
                    }
                    if (!state.isConnected) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(horizontal = paddingM, vertical = paddingS)
                                .shadow(elevation = sizeS),
                            backgroundColor = MaterialTheme.colors.error
                        ) {
                            BodyText(
                                text = stringResource(R.string.no_connection_message),
                                color = MaterialTheme.colors.onError,
                                modifier = Modifier.padding(all = paddingL)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainMenuItem(
    @StringRes label: Int,
    isConnected: Boolean,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = paddingM)
            .shadow(elevation = sizeS)
    ) {
        Button(onClick = { onClicked() }, enabled = isConnected) {
            BodyText(
                text = stringResource(label),
                modifier = Modifier.padding(horizontal = paddingM, vertical = paddingL)
            )
        }
    }
}

@Preview
@Composable
fun MainViewPreview() {
    BaseView {
        // MainView(flowOf(MainState(true, MenuItem.COINS))) {}
    }
}
