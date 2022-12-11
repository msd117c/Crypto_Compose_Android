package com.msd.latest_coins.list.presenter.helper

import androidx.paging.PagingData
import com.msd.latest_coins.common.TestDataBuilder.buildSortByOption
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.latest_coins_list.FetchLatestCoinsUseCase
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class FetchLatestModelsHelperTest : CoroutineTest() {

    private val fetchLatestCoinsUseCase: FetchLatestCoinsUseCase = mock()
    private val helper = FetchLatestModelsHelper(fetchLatestCoinsUseCase)

    @Test
    fun `when fetching latest coin models should invoke the use case and return the expected data`() =
        runTest {

            val results = mutableListOf<PagingData<LatestCoin>>()
            val job = launch { helper(buildSortByOption()).toList(results) }
            advanceUntilIdle()

            assert(results.size == 1)
            job.cancel()
        }
}