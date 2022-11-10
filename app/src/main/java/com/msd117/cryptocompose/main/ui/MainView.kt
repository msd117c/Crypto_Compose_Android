package com.msd117.cryptocompose.main.ui

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.msd117.cryptocompose.utils.NavigationEvent.Route

@Composable
fun MainView(viewModel: MainViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is MainState.Uninitialized -> Unit
            is MainState.Loaded -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    MainMenuView(
                        state = state,
                        onClicked = { route -> viewModel.navigate(Route(route)) }
                    )
                    MainConnectionErrorView(isConnectedProvider = state::isConnected)
                }
            }
        }
    }
}

@Composable
private fun MainMenuView(state: MainState.Loaded, onClicked: (String) -> Unit) {
    LazyColumn {
        with(state.items) {
            items(
                count = size,
                key = { index -> get(index).route }
            ) { index ->
                with(get(index)) {
                    MainMenuItem(
                        labelProvider = ::label,
                        isConnectedProvider = state::isConnected
                    ) {
                        onClicked(route)
                    }
                }
            }
        }
    }
}

@Composable
private fun MainMenuItem(
    @StringRes labelProvider: () -> Int,
    isConnectedProvider: () -> Boolean,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = paddingM)
            .shadow(elevation = sizeS)
    ) {
        Button(onClick = { onClicked() }, enabled = isConnectedProvider()) {
            BodyText(
                text = stringResource(labelProvider()),
                modifier = Modifier.padding(horizontal = paddingM, vertical = paddingL)
            )
        }
    }
}

@Composable
private fun BoxScope.MainConnectionErrorView(isConnectedProvider: () -> Boolean) {
    if (!isConnectedProvider()) {
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

@Preview
@Composable
fun MainViewPreview() {
    BaseView {
        // MainView(flowOf(MainState(true, MenuItem.COINS))) {}
    }
}
