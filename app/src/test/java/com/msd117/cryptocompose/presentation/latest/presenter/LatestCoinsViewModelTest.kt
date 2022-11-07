package com.msd117.cryptocompose.presentation.latest.presenter

import androidx.paging.PagingData
import androidx.paging.map
import com.msd117.cryptocompose.TestModels.LatestCoinModels.latestCoin
import com.msd117.cryptocompose.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.latest.presenter.LatestCoinsViewModel
import com.msd117.cryptocompose.latest.presenter.helper.FetchLatestModelsHelper
import com.msd117.cryptocompose.latest.presenter.model.LatestCoin
import com.msd117.cryptocompose.utils.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class LatestCoinsViewModelTest : ViewModelTest<LatestCoinsViewModel>() {

    private val fetchLatestModelsHelper: FetchLatestModelsHelper = mock()
    override val viewModel = LatestCoinsViewModel(scope, fetchLatestModelsHelper)

    @Test
    fun `when success on initialization should return the expected states`() {
        runBlockingTest {
            val pagingData = flowOf(PagingData.from(listOf(latestCoin)))
            whenever(fetchLatestModelsHelper()).thenReturn(pagingData)
            val expectedStates = listOf(
                LatestCoinsState.Uninitialized,
                LatestCoinsState.Loading,
                LatestCoinsState.Loaded(pagingData)
            )
            val states = mutableListOf<LatestCoinsState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(fetchLatestModelsHelper, only()).invoke()
                assert(expectedStates.take(2) == states.take(2))
                val expectedLatestCoins = mutableListOf<PagingData<LatestCoin>>()
                val latestCoins = mutableListOf<PagingData<LatestCoin>>()
                launch {
                    (expectedStates[2] as LatestCoinsState.Loaded).latestCoins.toList(
                        expectedLatestCoins
                    )
                    (states[2] as LatestCoinsState.Loaded).latestCoins.toList(latestCoins)
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
        runBlockingTest {
            whenever(fetchLatestModelsHelper()).thenAnswer { throw IOException() }
            val expectedStates = listOf(
                LatestCoinsState.Uninitialized,
                LatestCoinsState.Loading,
                LatestCoinsState.Error
            )
            val states = mutableListOf<LatestCoinsState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(fetchLatestModelsHelper, only()).invoke()
                assert(expectedStates == states)
                cancel()
            }
        }
    }

    @Test
    fun `when already initialized should not initialize again`() =
        runBlockingTest {
            whenever(fetchLatestModelsHelper()).thenAnswer { throw IOException() }
            viewModel.initialize()
            val expectedStates = listOf(LatestCoinsState.Error)
            val states = mutableListOf<LatestCoinsState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(fetchLatestModelsHelper, only()).invoke()
                assert(expectedStates == states)
                cancel()
            }
        }
}
