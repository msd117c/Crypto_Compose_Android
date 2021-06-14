package com.msd117.cryptocompose.presentation.main.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.main.presenter.MainState
import com.msd117.cryptocompose.presentation.main.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

enum class MenuItem(@StringRes val label: Int) {
    COINS(R.string.menu_item_coins),
    HISTORY(R.string.menu_item_history),
    MARKETS(R.string.menu_item_markets)
}

@Composable
fun MainView(mainStateFlow: Flow<MainState>, onClicked: (menuItem: MenuItem) -> Unit) {
    val mainState = mainStateFlow.collectAsState(initial = initialState)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            MainMenuItem(
                menuItem = MenuItem.COINS,
                isConnected = mainState.value.isConnected,
                onClicked = onClicked
            )
            MainMenuItem(
                menuItem = MenuItem.HISTORY,
                isConnected = mainState.value.isConnected,
                onClicked = onClicked
            )
            MainMenuItem(
                menuItem = MenuItem.MARKETS,
                isConnected = mainState.value.isConnected,
                onClicked = onClicked
            )
        }
        if (!mainState.value.isConnected) {
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

@Composable
fun MainMenuItem(
    menuItem: MenuItem,
    isConnected: Boolean,
    onClicked: (menuItem: MenuItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp)
    ) {
        Button(onClick = { onClicked(menuItem) }, enabled = isConnected) {
            Text(
                text = stringResource(menuItem.label),
                modifier = Modifier.padding(8.dp, 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainViewPreview() {
    BaseView {
        MainView(flowOf(MainState(true))) {}
    }
}