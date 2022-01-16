package com.msd117.cryptocompose.presentation.latest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.BaseView
import com.valentinilk.shimmer.shimmer

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
                Box(
                    modifier = Modifier
                        .padding(12.dp, 4.dp, 0.dp, 4.dp)
                        .requiredSize(52.dp)
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .shimmer()
                        .background(color = Color.Gray),
                )
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp, 2.dp)
                            .width(70.dp)
                            .height(18.dp)
                            .shimmer()
                            .background(color = Color.Gray)
                    )
                    Box(
                        modifier = Modifier
                            .padding(8.dp, 2.dp)
                            .width(50.dp)
                            .height(18.dp)
                            .shimmer()
                            .background(color = Color.Gray)
                    )
                }
            }
            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                Box(
                    modifier = Modifier
                        .padding(8.dp, 2.dp)
                        .width(90.dp)
                        .height(18.dp)
                        .shimmer()
                        .background(color = Color.Gray)
                        .align(Alignment.End)
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp, 2.dp)
                        .width(50.dp)
                        .height(18.dp)
                        .shimmer()
                        .background(color = Color.Gray)
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