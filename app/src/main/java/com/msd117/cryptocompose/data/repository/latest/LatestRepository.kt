package com.msd117.cryptocompose.data.repository.latest

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msd117.cryptocompose.data.model.latest.Data
import com.msd117.cryptocompose.data.network.latest.LatestNetwork
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

private const val PAGE_SIZE = 20

class LatestRepository @Inject constructor(retrofitClient: Retrofit) : PagingSource<Int, Data>() {

    private val latestNetwork = retrofitClient.create(LatestNetwork::class.java)

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = (params.key ?: 1)
            val start = (nextPage - 1).times(PAGE_SIZE) + 1
            val latestCoinList = latestNetwork.fetchLatest(start = start, limit = PAGE_SIZE)
            LoadResult.Page(
                data = latestCoinList.data.orEmpty(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (latestCoinList.data.isNullOrEmpty()) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}