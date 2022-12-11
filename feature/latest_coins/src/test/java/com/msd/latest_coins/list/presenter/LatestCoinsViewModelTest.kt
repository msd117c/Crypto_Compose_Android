package com.msd.latest_coins.list.presenter

import androidx.paging.PagingData
import com.msd.latest_coins.common.TestDataBuilder.buildLatestCoin
import com.msd.latest_coins.common.TestDataBuilder.buildSortByOption
import com.msd.latest_coins.list.presenter.LatestCoinsState.*
import com.msd.latest_coins.list.presenter.helper.FetchLatestModelsHelper
import com.msd.latest_coins.list.presenter.helper.GetLatestCoinSortByOptionsHelper
import com.msd.core.presentation.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.*
import java.io.IOException

@ExperimentalCoroutinesApi
class LatestCoinsViewModelTest : ViewModelTest<LatestCoinsState, LatestCoinsViewModel>() {

    private val fetchLatestModelsHelper: FetchLatestModelsHelper = mock()
    private val getLatestCoinSortByOptionsHelper: GetLatestCoinSortByOptionsHelper = mock()
    override val viewModel = LatestCoinsViewModel(
        getLatestCoinSortByOptionsHelper,
        fetchLatestModelsHelper
    )

    private val sortByOptions = listOf(buildSortByOption())

    @Test
    fun `when success on initialization should return the expected states`() = test(
        conditions = {
            val pagingData = flowOf(PagingData.from(listOf(buildLatestCoin())))
            whenever(getLatestCoinSortByOptionsHelper()).thenReturn(sortByOptions)
            whenever(fetchLatestModelsHelper(sortByOptions.first())).thenReturn(pagingData)
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results.take(2) == listOf(Uninitialized, Loading))
            assert((results.last() as Loaded).sortByOptions == sortByOptions)
            verify(fetchLatestModelsHelper, only()).invoke(sortByOptions.first())
        }
    )

    @Test
    fun `when error on initialization should return the expected states`() = test(
        conditions = {
            whenever(getLatestCoinSortByOptionsHelper()).thenReturn(sortByOptions)
            whenever(fetchLatestModelsHelper(sortByOptions.first())).thenAnswer { throw IOException() }
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Uninitialized, Loading, Error))
            verify(fetchLatestModelsHelper, only()).invoke(sortByOptions.first())
        }
    )

    @Test
    fun `when already initialized should not initialize again`() = test(
        conditions = {
            whenever(fetchLatestModelsHelper(sortByOptions.first())).thenAnswer { throw IOException() }
            viewModel.initialize()
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Error))
            verifyNoInteractions(fetchLatestModelsHelper)
        }
    )
}
