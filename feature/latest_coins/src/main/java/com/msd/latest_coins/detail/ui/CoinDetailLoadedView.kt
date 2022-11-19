package com.msd.latest_coins.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.msd.core.ui.theme.CryptoComposeTheme
import com.msd.core.ui.theme.Padding.paddingL
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Size.smallIconSize
import com.msd.core.ui.theme.Size.xSmallIconSize
import com.msd.core.ui.theme.ui.widget.BodyText
import com.msd.core.ui.theme.ui.widget.CardIconButtonView
import com.msd.core.ui.theme.ui.widget.SmallBodyText
import com.msd.core.ui.theme.zero
import com.msd.latest_coins.R
import com.msd.latest_coins.detail.presenter.model.CoinDetail

@ExperimentalMaterialApi
@Composable
fun CoinDetailLoadedView(coinDetailProvider: () -> CoinDetail) {
    with(coinDetailProvider()) {
        Column(modifier = Modifier.padding(all = paddingL)) {
            SmallBodyText(text = dateAdded)
            BodyText(text = description)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = zero, vertical = paddingL),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                technicalButtons.forEach { technicalButton ->
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
                urlButtons.forEach { urlButton ->
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
            coinDetailProvider = {
                CoinDetail(
                    name = "Bitcoin",
                    symbol = "BTC",
                    description = "description",
                    dateAdded = "",
                    tagNames = listOf("tagNames"),
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
            }
        )
    }
}
