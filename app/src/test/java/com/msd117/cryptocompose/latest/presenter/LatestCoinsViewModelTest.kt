package com.msd117.cryptocompose.latest.presenter

import androidx.paging.PagingData
import androidx.paging.map
import com.msd117.cryptocompose.TestModels.LatestCoinModels.latestCoin
import com.msd117.cryptocompose.TestModels.LatestCoinModels.sortByOptions
import com.msd.latest_coins_list.presenter.LatestCoinsState.*
import com.msd.latest_coins_list.presenter.helper.FetchLatestModelsHelper
import com.msd.latest_coins_list.presenter.helper.GetLatestCoinSortByOptionsHelper
import com.msd.latest_coins_list.presenter.model.LatestCoin
import com.msd117.cryptocompose.utils.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class LatestCoinsViewModelTest : ViewModelTest<com.msd.latest_coins_list.presenter.LatestCoinsViewModel>() {

    private val fetchLatestModelsHelper: com.msd.latest_coins_list.presenter.helper.FetchLatestModelsHelper = mock()
    private val getLatestCoinSortByOptionsHelper: com.msd.latest_coins_list.presenter.helper.GetLatestCoinSortByOptionsHelper = mock()
    override val viewModel = com.msd.latest_coins_list.presenter.LatestCoinsViewModel(
        scope,
        getLatestCoinSortByOptionsHelper,
        fetchLatestModelsHelper
    )

    @Test
    fun `when success on initialization should return the expected states`() {
        runTest {
            val pagingData = flowOf(PagingData.from(listOf(latestCoin)))
            whenever(fetchLatestModelsHelper(sortByOptions.first())).thenReturn(pagingData)
            val expectedStates = listOf(Uninitialized, Loading, Loaded(sortByOptions, pagingData))
            val states = mutableListOf<com.msd.latest_coins_list.presenter.LatestCoinsState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(fetchLatestModelsHelper, only()).invoke(sortByOptions.first())
                assert(expectedStates.take(2) == states.take(2))
                val expectedLatestCoins = mutableListOf<PagingData<com.msd.latest_coins_list.presenter.model.LatestCoin>>()
                val latestCoins = mutableListOf<PagingData<com.msd.latest_coins_list.presenter.model.LatestCoin>>()
                launch {
                    (expectedStates[2] as Loaded).latestCoins.toList(
                        expectedLatestCoins
                    )
                    (states[2] as Loaded).latestCoins.toList(latestCoins)
                }.apply {
                    assert(expectedLatestCoins.first().map { it } == latestCoins.first().map { it })
                    cancel()
                }
                cancel()
            }
        }
    }

    @Test
    fun `when error on initialization should return the expected states`() {
        runTest {
            whenever(fetchLatestModelsHelper(sortByOptions.first())).thenAnswer { throw IOException() }
            val expectedStates = listOf(Uninitialized, Loading, Error)
            val states = mutableListOf<com.msd.latest_coins_list.presenter.LatestCoinsState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(fetchLatestModelsHelper, only()).invoke(sortByOptions.first())
                assert(expectedStates == states)
                cancel()
            }
        }
    }

    @Test
    fun `when already initialized should not initialize again`() =
        runTest {
            whenever(fetchLatestModelsHelper(sortByOptions.first())).thenAnswer { throw IOException() }
            viewModel.initialize()
            val expectedStates = listOf(Error)
            val states = mutableListOf<com.msd.latest_coins_list.presenter.LatestCoinsState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(fetchLatestModelsHelper, only()).invoke(sortByOptions.first())
                assert(expectedStates == states)
                cancel()
            }
        }
}
