package com.msd117.cryptocompose.categories.ui

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
import com.msd117.cryptocompose.theme.Padding.paddingM
import com.msd117.cryptocompose.theme.Padding.paddingS
import com.msd117.cryptocompose.theme.Padding.paddingXS
import com.msd117.cryptocompose.theme.Size
import com.msd117.cryptocompose.theme.ui.loading.Height
import com.msd117.cryptocompose.theme.ui.loading.LoadingText
import com.msd117.cryptocompose.theme.zero

private const val LOADING_ITEMS = 10

@Composable
fun CategoriesLoadingView() {
    LazyColumn(modifier = Modifier.fillMaxSize()) { loadingItemsView() }
}

fun LazyListScope.loadingItemsView() {
    repeat(LOADING_ITEMS) { item { LoadingItemView() } }
}

@Composable
fun LoadingItemView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingM, vertical = paddingS)
            .shadow(elevation = Size.sizeS)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = zero, vertical = paddingS)
        ) {
            Row {
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    LoadingText(
                        width = 70.dp,
                        height = Height.Medium,
                        modifier = Modifier.padding(
                            horizontal = paddingM,
                            vertical = paddingXS
                        )
                    )
                    LoadingText(
                        width = 50.dp,
                        height = Height.Medium,
                        modifier = Modifier.padding(
                            horizontal = paddingM,
                            vertical = paddingXS
                        )
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
fun CategoriesLoadingPreview() {
    BaseView { CategoriesLoadingView() }
}
