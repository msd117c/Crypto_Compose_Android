package com.msd117.cryptocompose.presentation.latest.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.ui.ChangeIndicatorView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun LatestCoinsView(stateFlow: Flow<LatestCoinsState>) {
    val currentState by stateFlow.collectAsState(initial = initialState)
    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is LatestCoinsState.Loading -> LatestCoinLoadingView()
            is LatestCoinsState.Error -> LatestCoinErrorView()
            is LatestCoinsState.Loaded -> LatestCoinLoadedView(latestCoins = state.latestCoins)
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
fun LatestCoinLoadedView(latestCoins: List<LatestCoin>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        latestCoins.forEach { latestCoin ->
            item {
                LatestCoinItemView(latestCoin = latestCoin)
            }
        }
    }
}

@Composable
fun LatestCoinItemView(latestCoin: LatestCoin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = latestCoin.name,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .align(Alignment.CenterStart)
            )
            /*   Text(
                   text = latestCoin.symbol,
                   modifier = Modifier
                       .align(Alignment.CenterEnd)
                       .padding(8.dp, 16.dp)
               )*/
            ChangeIndicatorView(
                isPositive = latestCoin.usd?.isPositive ?: false,
                Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterEnd)
            )
            if (latestCoin.usd?.isPositive != null) {
                /* ChangeIndicatorView(
                     isPositive = latestCoin.usd.isPositive,
                     Modifier
                         .fillMaxHeight()
                         .width(30.dp)
                         .align(Alignment.CenterEnd)
                 )*/
            }
        }
    }
}

@Preview
@Composable
fun LatestCoinsViewPreview() {
    BaseView {
        //LatestCoinsView(flowOf(initialState))
        LatestCoinItemView(latestCoin = LatestCoin(

        ))
    }
}