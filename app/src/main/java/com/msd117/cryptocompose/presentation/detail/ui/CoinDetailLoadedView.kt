package com.msd117.cryptocompose.presentation.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.detail.model.CoinDetail
import com.msd117.cryptocompose.presentation.detail.model.CoinPlatform
import com.msd117.cryptocompose.presentation.detail.model.ContractAddress
import com.msd117.cryptocompose.theme.CryptoComposeTheme
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterialApi
@Composable
fun CoinDetailLoadedView(coinDetail: CoinDetail) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Row {
                    GlideImage(
                        imageModel = coinDetail.logo,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(40.dp)
                            .align(Alignment.CenterVertically),
                        shimmerParams = ShimmerParams(
                            baseColor = MaterialTheme.colors.background,
                            highlightColor = Color.LightGray,
                            durationMillis = 600,
                            dropOff = 0.65f,
                            tilt = 20f
                        )
                    )
                    Text(
                        text = coinDetail.name,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp, 0.dp)
                    )
                }
            },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = coinDetail.description)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                coinDetail.technicalButtons.forEach { technicalButton ->
                    item {
                        Card(
                            onClick = { },
                            modifier = Modifier
                                .padding(4.dp)
                                .shadow(4.dp)
                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                GlideImage(
                                    imageModel = technicalButton.icon,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.requiredSize(40.dp)
                                )
                                Text(
                                    text = LocalContext.current.getString(technicalButton.label),
                                    modifier = Modifier
                                        .padding(8.dp, 0.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                coinDetail.urlButtons.forEach { urlButton ->
                    item {
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .clip(CircleShape)
                                .requiredSize(44.dp)
                                .padding(4.dp)
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = urlButton.icon),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun CoinDetailLoadedPreview() {
    CryptoComposeTheme {
        CoinDetailLoadedView(
            coinDetail = CoinDetail(
                logo = "",
                id = 0,
                name = "Bitcoin",
                symbol = "BTC",
                slug = "slug",
                description = "description",
                notice = "notice",
                dateAdded = "",
                tags = emptyList(),
                category = "category",
                platform = CoinPlatform("Coin", null),
                tagNames = listOf("tagNames"),
                tagGroups = listOf("tagGroups"),
                twitterUsername = "twitterUsername",
                isHidden = 0,
                dateLaunched = "dateLaunched",
                contractAddress = listOf(ContractAddress("address", CoinPlatform("Coin", null))),
                selfReportedCirculatingSupply = "selfReportedCirculatingSupply",
                selfReportedTags = listOf("selfReportedTags"),
                technicalButtons = emptyList(),
                urlButtons = listOf(
                    CoinDetail.UrlButton(R.drawable.ic_twitter, "urls.twitter"),
                    CoinDetail.UrlButton(R.drawable.ic_reddit, "urls.reddit"),
                )
            )
        )
    }
}
