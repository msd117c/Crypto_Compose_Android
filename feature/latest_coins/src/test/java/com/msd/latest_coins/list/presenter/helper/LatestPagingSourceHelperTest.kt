package com.msd.latest_coins.list.presenter.helper

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.msd.latest_coins.common.TestDataBuilder.buildLatestCoin
import com.msd.latest_coins.common.TestDataBuilder.buildLatestCoinDomain
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.latest_coins_list.FetchLatestCoinsUseCase
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LatestPagingSourceHelperTest : CoroutineTest() {

    private val fetchLatestCoinsUseCase: FetchLatestCoinsUseCase = mock()

    @Test
    fun `when loading successfully should return the expected data`() = runTest {
        val latestCoinsDomain = listOf(buildLatestCoinDomain())
        val expectedLatestCoin = listOf(buildLatestCoin())
        val expected = LoadResult.Page(
            data = expectedLatestCoin,
            prevKey = null,
            nextKey = 2
        )
        whenever(fetchLatestCoinsUseCase(any(), any(), any())).thenReturn(latestCoinsDomain)
        val pagingSource = LatestPagingSourceHelper(fetchLatestCoinsUseCase, "sort")

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        assert(result == expected)
    }

    @Test
    fun `when loading with errors should return the expected error`() = runTest {
        val exception = RuntimeException()
        val expected = LoadResult.Error<Int, LatestCoin>(exception)
        whenever(fetchLatestCoinsUseCase(any(), any(), any())).thenThrow(exception)
        val pagingSource = LatestPagingSourceHelper(fetchLatestCoinsUseCase, "sort")

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assert(result == expected)
    }
}
