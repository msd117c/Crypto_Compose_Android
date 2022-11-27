package com.msd.latest_coins.list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.msd.core.ui.theme.BaseView
import com.msd.core.ui.theme.Padding.paddingM
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Padding.paddingXL
import com.msd.core.ui.theme.Padding.paddingXS
import com.msd.core.ui.theme.Size.sizeS
import com.msd.core.ui.theme.Size.smallIconSize
import com.msd.core.ui.theme.Size.topBarHeight
import com.msd.core.ui.theme.ui.image.UrlImage
import com.msd.core.ui.theme.ui.shared.SharedElement
import com.msd.core.ui.theme.ui.shared.SharedElementInfo
import com.msd.core.ui.theme.ui.shared.SharedElementRoot
import com.msd.core.ui.theme.ui.widget.BodyText
import com.msd.core.ui.theme.ui.widget.SelectableChip
import com.msd.core.ui.theme.ui.widget.SmallBodyText
import com.msd.core.ui.theme.ui.widget.TitleText
import com.msd.latest_coins.R
import com.msd.latest_coins.list.presenter.LatestCoinsState
import com.msd.latest_coins.list.presenter.model.LatestCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LatestCoinLoadedView(
    sortByOptionsProvider: () -> List<LatestCoinsState.Loaded.SortBy>,
    latestCoinsProvider: () -> Flow<PagingData<LatestCoin>>,
    onClick: (String, String, String) -> Unit,
    onSortByClicked: (String, Boolean) -> Unit
) {
    val topBarHeightPx = with(LocalDensity.current) { topBarHeight.roundToPx().toFloat() }
    val topBarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = topBarOffsetHeightPx.value + delta
                topBarOffsetHeightPx.value = newOffset.coerceIn(-topBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            LatestCoinSortByListView(
                sortByOptionsProvider = sortByOptionsProvider,
                onSortByClicked = onSortByClicked
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            LatestCoinListView(latestCoinsProvider = latestCoinsProvider, onClick = onClick)
            TopAppBar(
                modifier = Modifier
                    .height(topBarHeight)
                    .offset { IntOffset(x = 0, y = topBarOffsetHeightPx.value.roundToInt()) },
            ) {
                LatestCoinsTopBarView(bottomState = bottomState)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun LatestCoinsTopBarView(bottomState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleText(
                text = stringResource(R.string.latest_coins_title),
                modifier = Modifier.padding(all = paddingM)
            )
            Button(onClick = {
                coroutineScope.launch {
                    if (bottomState.isVisible) {
                        bottomState.hide()
                    } else {
                        bottomState.show()
                    }
                }
            }) { BodyText(text = stringResource(R.string.latest_coins_sort_by_button)) }
        }
    }
}

@Composable
private fun LatestCoinSortByListView(
    sortByOptionsProvider: () -> List<LatestCoinsState.Loaded.SortBy>,
    onSortByClicked: (String, Boolean) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingM, end = paddingM, bottom = paddingXL, top = paddingM)
    ) {
        sortByOptionsProvider().forEach { sortBy ->
            item { LatestCoinSortByView(sortBy = sortBy, onClick = onSortByClicked) }
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
        label = stringResource(label),
        contentDescription = "",
        selected = selected,
        onClick = { isSelected -> onClick(sortById, isSelected) }
    )
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
private fun LatestCoinListView(
    latestCoinsProvider: () -> Flow<PagingData<LatestCoin>>,
    onClick: (String, String, String) -> Unit
) {
    val latestCoinPagingItems = latestCoinsProvider().collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.padding(vertical = paddingM),
        state = listState,
        contentPadding = PaddingValues(top = topBarHeight)
    ) {
        with(latestCoinPagingItems) {
            items(
                count = itemCount,
                key = { index -> get(index)?.id ?: 0 }
            ) { index ->
                get(index)?.let { latestCoin ->
                    LatestCoinItemView(latestCoin = latestCoin, onClick = onClick)
                }
            }
            when {
                loadState.refresh is LoadState.Loading -> loadingItemsView()
                loadState.append is LoadState.Loading -> item { LoadingItemView() }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LatestCoinItemView(latestCoin: LatestCoin, onClick: (String, String, String) -> Unit) {
    with(latestCoin) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingM, vertical = paddingS)
                .shadow(elevation = sizeS),
            onClick = { onClick(symbol, icon, name) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = cmcRank,
                    style = TextStyle(
                        fontSize = 58.sp,
                        color = Color(0x68DADADA),
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = 48.dp, y = 0.dp),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(.6f)
                ) {
                    SharedElement(
                        tagProvider = ::symbol,
                        type = SharedElementInfo.SharedElementType.From,
                        modifier = Modifier
                            .requiredSize(smallIconSize)
                            .align(Alignment.CenterVertically)
                    ) {
                        UrlImage(::icon)
                    }
                    Column {
                        BodyText(
                            text = name,
                            modifier = Modifier.padding(
                                horizontal = paddingM,
                                vertical = paddingXS
                            )
                        )
                        SmallBodyText(
                            text = symbol,
                            modifier = Modifier.padding(
                                horizontal = paddingM,
                                vertical = paddingXS
                            )
                        )
                    }
                }
                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                    BodyText(
                        text = price,
                        modifier = Modifier
                            .padding(horizontal = paddingM, vertical = paddingXS)
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                    SmallBodyText(
                        text = summary,
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

@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun LatestCoinLoadedViewPreview() {
    BaseView {
        SharedElementRoot {
            LatestCoinItemView(
                LatestCoin(
                    id = 1,
                    name = "Bitcoin",
                    symbol = "BTC",
                    summary = "+0.5%",
                    growthColor = Color.Green,
                    price = "50",
                    icon = "",
                    cmcRank = "1"
                ),
                onClick = { _, _, _ -> },
            )
        }
    }
}
