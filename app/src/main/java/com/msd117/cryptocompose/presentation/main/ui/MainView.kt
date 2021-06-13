package com.msd117.cryptocompose.presentation.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.main.presenter.MainState
import com.msd117.cryptocompose.presentation.main.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

enum class MenuItem {
    COINS,
    HISTORY,
    MARKETS
}

@Composable
fun MainView(mainStateFlow: Flow<MainState>, onClicked: (menuItem: MenuItem) -> Unit) {
    val mainState = mainStateFlow.collectAsState(initial = initialState)
    Column(modifier = Modifier.fillMaxSize()) {
        MainMenuItem(
            label = LocalContext.current.getString(R.string.menu_item_coins),
            onClicked = onClicked,
            menuItem = MenuItem.COINS
        )
        MainMenuItem(
            label = LocalContext.current.getString(R.string.menu_item_history),
            onClicked = onClicked,
            menuItem = MenuItem.HISTORY
        )
        MainMenuItem(
            label = LocalContext.current.getString(R.string.menu_item_markets),
            onClicked = onClicked,
            menuItem = MenuItem.MARKETS
        )
    }
}

@Composable
fun MainMenuItem(label: String, onClicked: (menuItem: MenuItem) -> Unit, menuItem: MenuItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple()
            ) { onClicked(menuItem) }
            .shadow(4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}

@Preview
@Composable
fun MainViewPreview() {
    BaseView {
        MainView(flowOf(MainState(true))) {}
    }
}