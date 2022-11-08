package com.msd117.cryptocompose.latest.presenter.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msd117.cryptocompose.latest.domain.FetchLatestCoinsUseCase
import com.msd117.cryptocompose.latest.presenter.mapper.toPresentation
import com.msd117.cryptocompose.latest.presenter.model.LatestCoin
import retrofit2.HttpException
import java.io.IOException

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
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}
