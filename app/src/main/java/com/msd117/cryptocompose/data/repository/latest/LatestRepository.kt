package com.msd117.cryptocompose.data.repository.latest

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msd117.cryptocompose.data.model.latest.Data
import com.msd117.cryptocompose.data.network.latest.LatestNetwork
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class LatestRepository(
    retrofitClient: Retrofit,
    private val sort: String
) : PagingSource<Int, Data>() {

    private val latestNetwork = retrofitClient.create(LatestNetwork::class.java)

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = (params.key ?: 1)
            val start = (nextPage.minus(1)).times(params.loadSize).plus(1)
            val latestCoinList = latestNetwork.fetchLatest(
                start = start,
                limit = params.loadSize,
                sort = sort
            )
            LoadResult.Page(
                data = latestCoinList.data.orEmpty(),
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (latestCoinList.data.isNullOrEmpty()) null else nextPage.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}