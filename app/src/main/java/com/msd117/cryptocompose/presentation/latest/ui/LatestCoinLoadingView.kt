package com.msd117.cryptocompose.presentation.latest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.theme.ui.Height
import com.msd117.cryptocompose.theme.ui.LoadingCircle
import com.msd117.cryptocompose.theme.ui.LoadingText

@Composable
fun LatestCoinLoadingView() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        (0..10).forEach { _ ->
            item { LoadingItemView() }
        }
    }
}

@Composable
fun LoadingItemView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .shadow(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row {
                LoadingCircle(
                    size = 48.dp,
                    modifier = Modifier
                        .padding(14.dp, 6.dp, 0.dp, 6.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    LoadingText(
                        width = 70.dp,
                        height = Height.Medium,
                        modifier = Modifier.padding(8.dp, 2.dp)
                    )
                    LoadingText(
                        width = 50.dp,
                        height = Height.Medium,
                        modifier = Modifier.padding(8.dp, 2.dp)
                    )
                }
            }
            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                LoadingText(
                    width = 90.dp,
                    height = Height.Medium,
                    modifier = Modifier
                        .padding(8.dp, 2.dp)
                        .align(Alignment.End)
                )
                LoadingText(
                    width = 50.dp,
                    height = Height.Medium,
                    modifier = Modifier
                        .padding(8.dp, 2.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Preview
@Composable
fun LatestCoinLoadingPreview() {
    BaseView {
        LatestCoinLoadingView()
    }
}