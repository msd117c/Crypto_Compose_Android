package com.msd117.cryptocompose.presentation.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.theme.ui.Height
import com.msd117.cryptocompose.theme.ui.LoadingCircle
import com.msd117.cryptocompose.theme.ui.LoadingText

@ExperimentalMaterialApi
@Composable
fun CoinDetailLoadingView() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Row {
                    LoadingCircle(
                        size = 40.dp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    LoadingText(
                        width = 140.dp,
                        height = Height.Large,
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
        Column(
            modifier = Modifier.padding(16.dp, 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LoadingText(height = Height.Medium)
            LoadingText(height = Height.Medium)
            LoadingText(width = 150.dp, height = Height.Medium)
            LoadingText(height = Height.Medium)
            LoadingText(height = Height.Medium)
            LoadingText(height = Height.Medium)
            LoadingText(height = Height.Medium)
            LoadingText(width = 200.dp, height = Height.Medium)
            Card(
                onClick = { },
                modifier = Modifier.padding(0.dp, 8.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    LoadingCircle(size = 38.dp)
                    LoadingText(
                        width = 80.dp,
                        height = Height.Medium,
                        modifier = Modifier
                            .padding(8.dp, 0.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun CoinDetailLoadingPreview() {
    BaseView {
        CoinDetailLoadingView()
    }
}