package com.msd117.cryptocompose.presentation.latest.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.ui.ChangeIndicatorView
import kotlinx.coroutines.flow.Flow

@Composable
fun LatestCoinsView(stateFlow: Flow<LatestCoinsState>) {
    val currentState by stateFlow.collectAsState(initial = initialState)
    var showLatestCoinDetail by remember { mutableStateOf<LatestCoin?>(null) }
    val onClick: (LatestCoin) -> Unit = { latestCoin ->
        showLatestCoinDetail = latestCoin
    }
    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(
                latestCoins = state.latestCoins,
                onClick = onClick
            )
        }
    }

    showLatestCoinDetail?.let { latestCoin ->
        Dialog(onDismissRequest = { showLatestCoinDetail = null }) {
            LatestCoinDetailView(latestCoin = latestCoin)
        }
    }
}

@Composable
fun LatestCoinLoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun LatestCoinErrorView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Error",
            color = MaterialTheme.colors.error,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun LatestCoinLoadedView(latestCoins: List<LatestCoin>, onClick: (LatestCoin) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        latestCoins.forEach { latestCoin ->
            item {
                LatestCoinItemView(latestCoin = latestCoin, onClick = onClick)
            }
        }
    }
}

@Composable
fun LatestCoinItemView(latestCoin: LatestCoin, onClick: (LatestCoin) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp)
    ) {
        Button(onClick = { onClick(latestCoin) }) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = latestCoin.name,
                    modifier = Modifier
                        .padding(8.dp, 16.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = latestCoin.symbol,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(28.dp, 16.dp)
                )
                if (latestCoin.usd?.isPositive != null) {
                    ChangeIndicatorView(
                        isPositive = latestCoin.usd.isPositive,
                        Modifier
                            .fillMaxHeight()
                            .width(20.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

@Composable
fun LatestCoinDetailView(latestCoin: LatestCoin) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = latestCoin.name, modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = latestCoin.symbol, modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        )
    }
}

@Preview
@Composable
fun LatestCoinsViewPreview() {
    BaseView {
        LatestCoinDetailView(
            latestCoin = LatestCoin(
                name = "Bitcoin",
                symbol = "BTC"
            )
        )
    }
}