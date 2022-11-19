package com.msd.categories.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msd.categories.presenter.model.Category
import com.msd.core.ui.theme.BaseView
import com.msd.core.ui.theme.Padding.paddingM
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Size.sizeS
import com.msd.core.ui.theme.ui.widget.BodyText
import com.msd.core.ui.theme.ui.widget.SmallBodyText
import kotlinx.coroutines.flow.Flow

@Composable
fun CategoriesLoadedView(categories: Flow<PagingData<Category>>) {
    val categoryItems: LazyPagingItems<Category> = categories.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.padding(vertical = paddingM),
        state = listState
    ) {
        items(categoryItems.itemCount) { index ->
            categoryItems[index]?.let { item ->
                CategoryItemView(category = item)
            }
        }
        categoryItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> loadingItemsView()
                loadState.append is LoadState.Loading -> item { LoadingItemView() }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                    Log.d("LOADERR", "ERROR")
                }
            }
        }
    }
}

@Composable
fun CategoryItemView(category: Category) {
    with(category) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingS)
                .shadow(elevation = sizeS)
        ) {
            Box {
                Column(modifier = Modifier.padding(horizontal = paddingM, vertical = paddingS)) {
                    BodyText(text = name)
                    SmallBodyText(text = "Number of tokens: $numTokens")
                    SmallBodyText(text = "Volume: $volume")
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoriesLoadedPreview() {
    BaseView {
        CategoryItemView(
            Category(
                id = "",
                name = "A16Z Portfolio",
                title = "A16Z Portfolio",
                description = "A16Z Portfolio",
                numTokens = null,
                avgPriceChange = null,
                marketCap = null,
                marketCapChange = null,
                volume = null,
                volumeChange = null,
                lastUpdated = null
            )
        )
    }
}
