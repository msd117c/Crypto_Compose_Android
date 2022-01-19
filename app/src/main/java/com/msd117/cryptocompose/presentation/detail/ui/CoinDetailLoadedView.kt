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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.detail.model.CoinDetail
import com.msd117.cryptocompose.presentation.detail.model.CoinPlatform
import com.msd117.cryptocompose.presentation.detail.model.ContractAddress
import com.msd117.cryptocompose.theme.*
import com.msd117.cryptocompose.theme.ui.widget.CardIconButtonView
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
                            .requiredSize(smallIconSize)
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
                            .padding(horizontal = paddingM, vertical = zero)
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
                        .padding(all = paddingL)
                )
            }
        )
        Column(modifier = Modifier.padding(all = paddingL)) {
            Text(text = coinDetail.description)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = zero, vertical = paddingM),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                coinDetail.technicalButtons.forEach { technicalButton ->
                    item {
                        with(technicalButton) {
                            CardIconButtonView(icon = icon, label = label) { }
                        }
                    }
                }
            }
            LazyRow(horizontalArrangement = Arrangement.spacedBy(space = paddingL)) {
                coinDetail.urlButtons.forEach { urlButton ->
                    item {
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .clip(CircleShape)
                                .requiredSize(smallIconSize)
                                .padding(all = paddingS)
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
