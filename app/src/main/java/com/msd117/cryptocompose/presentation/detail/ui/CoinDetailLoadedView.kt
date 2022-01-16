package com.msd117.cryptocompose.presentation.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        Column(modifier = Modifier.padding(16.dp, 24.dp)) {
            Text(text = coinDetail.description)
            Card(
                onClick = { },
                modifier = Modifier.padding(0.dp, 8.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    GlideImage(
                        imageModel = R.drawable.ic_source_code,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(38.dp)
                    )
                    Text(
                        text = "Source code",
                        modifier = Modifier
                            .padding(8.dp, 0.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(0.dp, 32.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                GlideImage(
                    imageModel = R.drawable.ic_twitter,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(38.dp)
                )
                GlideImage(
                    imageModel = R.drawable.ic_reddit,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(38.dp)
                )
                GlideImage(
                    imageModel = R.drawable.ic_browser,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(38.dp)
                )
                GlideImage(
                    imageModel = R.drawable.ic_documentation,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(38.dp)
                )
                GlideImage(
                    imageModel = R.drawable.ic_chat,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(38.dp)
                )
                GlideImage(
                    imageModel = R.drawable.ic_announcement,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(38.dp)
                )
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
                website = listOf("urls.website"),
                technicalDoc = listOf("urls.technicalDoc"),
                twitter = listOf("urls.twitter"),
                reddit = listOf("urls.reddit"),
                messageBoard = listOf("urls.messageBoard"),
                announcement = listOf("urls.announcement"),
                chat = listOf("urls.chat"),
                explorer = listOf("urls.explorer"),
                sourceCode = listOf("urls.sourceCode"),
                subreddit = "subreddit",
                tagNames = listOf("tagNames"),
                tagGroups = listOf("tagGroups"),
                twitterUsername = "twitterUsername",
                isHidden = 0,
                dateLaunched = "dateLaunched",
                contractAddress = listOf(ContractAddress("address", CoinPlatform("Coin", null))),
                selfReportedCirculatingSupply = "selfReportedCirculatingSupply",
                selfReportedTags = listOf("selfReportedTags")
            )
        )
    }
}
