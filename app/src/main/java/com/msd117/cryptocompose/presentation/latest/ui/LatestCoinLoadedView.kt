package com.msd117.cryptocompose.presentation.latest.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msd117.cryptocompose.presentation.latest.model.Growth
import com.msd117.cryptocompose.presentation.latest.model.LatestCoin
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.theme.*
import com.msd117.cryptocompose.theme.ui.shared.SharedElement
import com.msd117.cryptocompose.theme.ui.shared.SharedElementInfo
import com.msd117.cryptocompose.theme.ui.widget.SelectableChip
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@ExperimentalComposeUiApi
@Composable
fun LatestCoinLoadedView(
    sortByOptions: List<LatestCoinsState.Loaded.SortBy>,
    latestCoins: Flow<PagingData<LatestCoin>>,
    onClick: (String, String, String) -> Unit,
    onSortByClicked: (String, Boolean) -> Unit
) {
    val latestCoinItems: LazyPagingItems<LatestCoin> = latestCoins.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = paddingM),
            horizontalArrangement = Arrangement.spacedBy(space = sizeS)
        ) {
            sortByOptions.forEach { sortBy ->
                item { LatestCoinSortByView(sortBy = sortBy, onClick = onSortByClicked) }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(latestCoinItems.itemCount) { index ->
                latestCoinItems[index]?.let { item ->
                    LatestCoinItemView(
                        latestCoin = item,
                        onClick = onClick
                    )
                }
            }
            latestCoinItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        (0..10).forEach { _ ->
                            item { LoadingItemView() }
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItemView() }
                    }
                    loadState.append is LoadState.Error -> {
                        //You can use modifier to show error message
                        Log.d("LOADERR", "ERROR")
                    }
                }
            }
        }
    }
}

@Composable
fun LatestCoinSortByView(
    sortBy: LatestCoinsState.Loaded.SortBy,
    onClick: (String, Boolean) -> Unit
) {
    val (sortById, label, selected) = sortBy
    SelectableChip(
        label = LocalContext.current.getString(label),
        contentDescription = "",
        selected = selected,
        onClick = { isSelected ->
            onClick(sortById, isSelected)
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun LatestCoinItemView(latestCoin: LatestCoin, onClick: (String, String, String) -> Unit) {
    val growthColor = when (latestCoin.growth) {
        Growth.POSITIVE -> Color.Green
        Growth.NEGATIVE -> Color.Red
        Growth.NONE -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingM, vertical = paddingS)
            .shadow(elevation = sizeS)
    ) {
        Button(onClick = { with(latestCoin) { onClick(symbol, icon, name) } }) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row {
                    SharedElement(
                        tag = latestCoin.symbol,
                        type = SharedElementInfo.SharedElementType.From,
                        modifier = Modifier
                            .requiredSize(smallIconSize)
                            .align(Alignment.CenterVertically)
                    ) {
                        GlideImage(
                            imageModel = latestCoin.icon,
                            contentScale = ContentScale.Crop,
                            shimmerParams = ShimmerParams(
                                baseColor = MaterialTheme.colors.background,
                                highlightColor = Color.LightGray,
                                durationMillis = 600,
                                dropOff = 0.65f,
                                tilt = 20f
                            ),
                        )
                    }
                    Column {
                        Text(
                            text = latestCoin.name,
                            modifier = Modifier.padding(
                                horizontal = paddingM,
                                vertical = paddingXS
                            )
                        )
                        Text(
                            text = latestCoin.symbol,
                            modifier = Modifier.padding(horizontal = paddingM, vertical = paddingXS)
                        )
                    }
                }
                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Text(
                        text = latestCoin.price,
                        modifier = Modifier
                            .padding(horizontal = paddingM, vertical = paddingXS)
                            .fillMaxWidth(),
                        color = Color.Black,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = latestCoin.summary,
                        modifier = Modifier
                            .padding(horizontal = paddingM, vertical = paddingXS)
                            .fillMaxWidth(),
                        color = growthColor,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun LatestCoinLoadedViewPreview() {
    BaseView {
        LatestCoinLoadedView(
            sortByOptions = emptyList(),
            latestCoins = flowOf(
                PagingData.from(
                    listOf(
                        LatestCoin(
                            name = "Bitcoin",
                            symbol = "BTC",
                            summary = "+0.5%",
                            growth = Growth.POSITIVE,
                            price = "50",
                            icon = "https://cryptoicon-api.vercel.app/api/icon/btc"
                        )
                    )
                )
            ),
            onClick = { _, _, _ -> },
            onSortByClicked = { _, _ -> }
        )
    }
}
