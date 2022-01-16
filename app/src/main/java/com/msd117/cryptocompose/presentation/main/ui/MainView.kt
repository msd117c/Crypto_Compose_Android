package com.msd117.cryptocompose.presentation.main.ui

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.main.presenter.MainState
import com.msd117.cryptocompose.presentation.main.presenter.MainViewModel
import com.msd117.cryptocompose.presentation.main.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.utils.NavigationConstants

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
                            onClicked = { navController.navigate(NavigationConstants.LatestCoinsRoute) }
                        )
                        MainMenuItem(
                            label = R.string.menu_item_history,
                            isConnected = state.isConnected,
                            onClicked = {}
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
                                .padding(8.dp, 4.dp)
                                .shadow(4.dp),
                            backgroundColor = MaterialTheme.colors.error
                        ) {
                            Text(
                                text = stringResource(R.string.no_connection_message),
                                color = MaterialTheme.colors.onError,
                                modifier = Modifier.padding(16.dp)
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
            .padding(8.dp)
            .shadow(4.dp)
    ) {
        Button(onClick = { onClicked() }, enabled = isConnected) {
            Text(
                text = stringResource(label),
                modifier = Modifier.padding(8.dp, 16.dp)
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