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
import com.msd.core.ui.theme.BaseView
import com.msd.core.ui.theme.Padding.paddingM
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Padding.paddingXS
import com.msd.core.ui.theme.Size
import com.msd.core.ui.theme.ui.loading.Height
import com.msd.core.ui.theme.ui.loading.LoadingText
import com.msd.core.ui.theme.zero

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
            .shadow(elevation = com.msd.core.ui.theme.Size.sizeS)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = com.msd.core.ui.theme.zero, vertical = paddingS)
        ) {
            Row {
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    com.msd.core.ui.theme.ui.loading.LoadingText(
                        width = 70.dp,
                        height = com.msd.core.ui.theme.ui.loading.Height.Medium,
                        modifier = Modifier.padding(
                            horizontal = paddingM,
                            vertical = paddingXS
                        )
                    )
                    com.msd.core.ui.theme.ui.loading.LoadingText(
                        width = 50.dp,
                        height = com.msd.core.ui.theme.ui.loading.Height.Medium,
                        modifier = Modifier.padding(
                            horizontal = paddingM,
                            vertical = paddingXS
                        )
                    )
                }
            }
            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                com.msd.core.ui.theme.ui.loading.LoadingText(
                    width = 90.dp,
                    height = com.msd.core.ui.theme.ui.loading.Height.Medium,
                    modifier = Modifier
                        .padding(horizontal = paddingM, vertical = paddingXS)
                        .align(Alignment.End)
                )
                com.msd.core.ui.theme.ui.loading.LoadingText(
                    width = 50.dp,
                    height = com.msd.core.ui.theme.ui.loading.Height.Medium,
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
    com.msd.core.ui.theme.BaseView { CategoriesLoadingView() }
}
