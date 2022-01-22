package com.msd117.cryptocompose.presentation.latest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.msd117.cryptocompose.presentation.latest.model.Growth
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.theme.*
import com.msd117.cryptocompose.theme.ui.shared.SharedElement
import com.msd117.cryptocompose.theme.ui.shared.SharedElementInfo
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalComposeUiApi
@Composable
fun LatestCoinLoadedView(latestCoins: List<LatestCoin>, onClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        latestCoins.forEach { latestCoin ->
            item { LatestCoinItemView(latestCoin = latestCoin, onClick = onClick) }
        }
    }
}

@ExperimentalComposeUiApi
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
            .padding(horizontal = paddingM, vertical = paddingS)
            .shadow(elevation = sizeS)
    ) {
        Button(onClick = { onClick(latestCoin.symbol) }) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row {
                    SharedElement(
                        tag = "coin", type = SharedElementInfo.SharedElementType.From, modifier = Modifier
                            .requiredSize(smallIconSize)
                            .align(Alignment.CenterVertically)
                    ) {
                        GlideImage(
                            imageModel = latestCoin.icon,
                            contentScale = ContentScale.Crop,
                            shimmerParams = ShimmerParams(
                                baseColor = MaterialTheme.colors.background,
                                highlightColor = Color.LightGray,
                                durationMillis = 600,
                                dropOff = 0.65f,
                                tilt = 20f
                            ),
                        )
                    }
                    Column {
                        Text(
                            text = latestCoin.name,
                            modifier = Modifier.padding(
                                horizontal = paddingM,
                                vertical = paddingXS
                            ),
                            color = Color.Black
                        )
                        Text(
                            text = latestCoin.symbol,
                            modifier = Modifier.padding(horizontal = paddingM, vertical = paddingXS)
                        )
                    }
                }
                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Text(
                        text = latestCoin.price,
                        modifier = Modifier
                            .padding(horizontal = paddingM, vertical = paddingXS)
                            .fillMaxWidth(),
                        color = Color.Black,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = latestCoin.summary,
                        modifier = Modifier
                            .padding(horizontal = paddingM, vertical = paddingXS)
                            .fillMaxWidth(),
                        color = growthColor,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
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
