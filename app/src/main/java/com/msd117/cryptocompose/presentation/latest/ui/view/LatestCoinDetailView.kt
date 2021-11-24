package com.msd117.cryptocompose.presentation.latest.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.presentation.latest.model.Growth
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.theme.BaseView

@Composable
fun LatestCoinDetailView(latestCoin: LatestCoin) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
        Text(text = latestCoin.summary, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun LatestCoinDetailViewPreview() {
    BaseView {
        LatestCoinDetailView(
            latestCoin = LatestCoin(
                name = "Bitcoin",
                symbol = "BTC",
                summary = "BTC (+0.5%)",
                growth = Growth.POSITIVE,
                price = "50",
                icon = ""
            )
        )
    }
}
