package com.msd.categories.presenter.helper

import androidx.paging.PagingData
import com.msd.domain.categories.FetchCategoriesUseCase
import com.msd.categories.presenter.model.Category
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class FetchCategoriesHelperTest : CoroutineTest() {

    private val fetchCategoriesUseCase: FetchCategoriesUseCase = mock()
    private val helper = FetchCategoriesHelper(fetchCategoriesUseCase)

    @Test
    fun `when fetching categories should invoke the use case and return the expected data`() =
        runTest {

            val results = mutableListOf<PagingData<Category>>()
            val job = launch { helper().toList(results) }
            advanceUntilIdle()

            assert(results.size == 1)
            job.cancel()
        }
}