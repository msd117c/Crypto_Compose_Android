package com.msd117.cryptocompose.detail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.*
import com.msd117.cryptocompose.theme.Padding.paddingL
import com.msd117.cryptocompose.theme.Padding.paddingM
import com.msd117.cryptocompose.theme.Padding.paddingS
import com.msd117.cryptocompose.theme.Size.sizeS
import com.msd117.cryptocompose.theme.Size.smallIconSize
import com.msd117.cryptocompose.theme.ui.loading.Height
import com.msd117.cryptocompose.theme.ui.loading.LoadingCircle
import com.msd117.cryptocompose.theme.ui.loading.LoadingText

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun CoinDetailLoadingView() {
    Column(modifier = Modifier.padding(all = paddingL)) {
        CoinDetailTechnicalDescriptionLoadingView()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = zero, vertical = paddingM),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CoinDetailTechnicalButtonLoadingView()
            CoinDetailTechnicalButtonLoadingView()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CoinDetailTechnicalDescriptionLoadingView() {
    Column(verticalArrangement = Arrangement.spacedBy(space = paddingM)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(space = paddingM)) {
            (0..1).forEach { _ -> item { LoadingText(height = Height.Medium) } }
        }
        LoadingText(width = 150.dp, height = Height.Medium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(space = paddingM)) {
            (0..4).forEach { _ -> item { LoadingText(height = Height.Medium) } }
        }
        LoadingText(width = 200.dp, height = Height.Medium)
    }
}

@ExperimentalMaterialApi
@Composable
fun CoinDetailTechnicalButtonLoadingView() {
    Card(
        onClick = { },
        modifier = Modifier
            .padding(all = paddingS)
            .shadow(elevation = sizeS)
    ) {
        Row(modifier = Modifier.padding(all = paddingM)) {
            LoadingCircle(size = smallIconSize)
            LoadingText(
                width = 100.dp,
                height = Height.Medium,
                modifier = Modifier
                    .padding(horizontal = paddingM, vertical = zero)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
@Preview
fun CoinDetailLoadingPreview() {
    BaseView {
        CoinDetailLoadingView()
    }
}
