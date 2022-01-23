package com.msd117.cryptocompose.presentation.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.detail.model.CoinDetail
import com.msd117.cryptocompose.presentation.detail.model.CoinPlatform
import com.msd117.cryptocompose.presentation.detail.model.ContractAddress
import com.msd117.cryptocompose.theme.*
import com.msd117.cryptocompose.theme.ui.widget.CardIconButtonView

@ExperimentalMaterialApi
@Composable
fun CoinDetailLoadedView(coinDetail: CoinDetail) {
    Column(modifier = Modifier.padding(all = paddingL)) {
        Text(text = coinDetail.description)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = zero, vertical = paddingL),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            coinDetail.technicalButtons.forEach { technicalButton ->
                with(technicalButton) {
                    CardIconButtonView(
                        icon = icon,
                        label = label,
                        iconSize = xSmallIconSize
                    ) { }
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
                technicalButtons = listOf(
                    CoinDetail.TechnicalButtons(
                        R.drawable.ic_twitter,
                        R.string.details_source_code_button,
                        "urls.twitter"
                    ),
                    CoinDetail.TechnicalButtons(
                        R.drawable.ic_reddit,
                        R.string.details_technical_docs_button,
                        "urls.reddit"
                    ),
                ),
                urlButtons = listOf(
                    CoinDetail.UrlButton(R.drawable.ic_twitter, "urls.twitter"),
                    CoinDetail.UrlButton(R.drawable.ic_reddit, "urls.reddit"),
                )
            )
        )
    }
}
