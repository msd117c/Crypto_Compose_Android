package com.msd.latest_coins.list.presenter.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msd.latest_coins.list.presenter.mapper.toPresentation
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.latest_coins_list.FetchLatestCoinsUseCase

class LatestPagingSourceHelper(
    private val fetchLatestCoinsUseCase: FetchLatestCoinsUseCase,
    private val sort: String
) : PagingSource<Int, LatestCoin>() {

    override fun getRefreshKey(state: PagingState<Int, LatestCoin>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LatestCoin> {
        return try {
            val nextPage = (params.key ?: 1)
            val start = (nextPage.minus(1)).times(params.loadSize).plus(1)
            val latestCoinList =
                fetchLatestCoinsUseCase(start, limit = params.loadSize, sort).toPresentation()
            LoadResult.Page(
                data = latestCoinList,
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (latestCoinList.isEmpty()) null else nextPage.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}
