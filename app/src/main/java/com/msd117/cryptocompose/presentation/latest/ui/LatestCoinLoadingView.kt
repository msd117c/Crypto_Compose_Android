package com.msd117.cryptocompose.presentation.latest.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.BaseView
import com.msd117.cryptocompose.theme.Padding.paddingL
import com.msd117.cryptocompose.theme.Padding.paddingM
import com.msd117.cryptocompose.theme.Padding.paddingS
import com.msd117.cryptocompose.theme.Padding.paddingXS
import com.msd117.cryptocompose.theme.Size.sizeS
import com.msd117.cryptocompose.theme.Size.smallLoadingIconSize
import com.msd117.cryptocompose.theme.ui.loading.Height
import com.msd117.cryptocompose.theme.ui.loading.LoadingCircle
import com.msd117.cryptocompose.theme.ui.loading.LoadingText
import com.msd117.cryptocompose.theme.zero

@Composable
fun LatestCoinLoadingView() {
    Log.d("LOADERR", "Loading 1")
    LazyColumn(modifier = Modifier.fillMaxSize()) { loadingItemsView() }
}

fun LazyListScope.loadingItemsView() {
    repeat(10) { item { LoadingItemView() } }
}

@Composable
fun LoadingItemView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingM, vertical = paddingS)
            .shadow(elevation = sizeS)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row {
                LoadingCircle(
                    size = smallLoadingIconSize,
                    modifier = Modifier
                        .padding(start = paddingL, top = paddingM, end = zero, bottom = paddingM)
                        .align(Alignment.CenterVertically)
                )
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    LoadingText(
                        width = 70.dp,
                        height = Height.Medium,
                        modifier = Modifier.padding(horizontal = paddingM, vertical = paddingXS)
                    )
                    LoadingText(
                        width = 50.dp,
                        height = Height.Medium,
                        modifier = Modifier.padding(horizontal = paddingM, vertical = paddingXS)
                    )
                }
            }
            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                LoadingText(
                    width = 90.dp,
                    height = Height.Medium,
                    modifier = Modifier
                        .padding(horizontal = paddingM, vertical = paddingXS)
                        .align(Alignment.End)
                )
                LoadingText(
                    width = 50.dp,
                    height = Height.Medium,
                    modifier = Modifier
                        .padding(horizontal = paddingM, vertical = paddingXS)
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