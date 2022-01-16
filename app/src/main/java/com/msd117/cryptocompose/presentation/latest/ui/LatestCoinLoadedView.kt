package com.msd117.cryptocompose.presentation.latest.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.presentation.latest.model.Growth
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.theme.BaseView
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LatestCoinLoadedView(latestCoins: List<LatestCoin>, onClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        latestCoins.forEach { latestCoin ->
            item { LatestCoinItemView(latestCoin = latestCoin, onClick = onClick) }
        }
    }
}

@Composable
fun LatestCoinItemView(latestCoin: LatestCoin, onClick: (String) -> Unit) {
    val growthColor = when (latestCoin.growth) {
        Growth.POSITIVE -> Color.Green
        Growth.NEGATIVE -> Color.Red
        Growth.NONE -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .shadow(4.dp)
    ) {
        Button(onClick = { onClick(latestCoin.symbol) }) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row {
                    GlideImage(
                        imageModel = latestCoin.icon,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(44.dp)
                            .align(Alignment.CenterVertically),
                        shimmerParams = ShimmerParams(
                            baseColor = MaterialTheme.colors.background,
                            highlightColor = Color.LightGray,
                            durationMillis = 600,
                            dropOff = 0.65f,
                            tilt = 20f
                        ),
                    )
                    Column {
                        Text(
                            text = latestCoin.name,
                            modifier = Modifier.padding(8.dp, 2.dp),
                            color = Color.Black
                        )
                        Text(
                            text = latestCoin.symbol,
                            modifier = Modifier.padding(8.dp, 2.dp)
                        )
                    }
                }
                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Text(
                        text = latestCoin.price,
                        modifier = Modifier
                            .padding(8.dp, 2.dp)
                            .fillMaxWidth(),
                        color = Color.Black,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = latestCoin.summary,
                        modifier = Modifier
                            .padding(8.dp, 2.dp)
                            .fillMaxWidth(),
                        color = growthColor,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LatestCoinLoadedViewPreview() {
    BaseView {
        LatestCoinLoadedView(
            latestCoins = listOf(
                LatestCoin(
                    name = "Bitcoin",
                    symbol = "BTC",
                    summary = "+0.5%",
                    growth = Growth.POSITIVE,
                    price = "50",
                    icon = "https://cryptoicon-api.vercel.app/api/icon/btc"
                )
            )
        ) {}
    }
}
