package com.msd117.cryptocompose.presentation.latest.presenter

import com.msd117.cryptocompose.TestModels.LatestCoinModels.latestCoin
import com.msd117.cryptocompose.presentation.latest.helper.FetchLatestModelsHelper
import com.msd117.cryptocompose.utils.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LatestCoinsViewModelTest : ViewModelTest<LatestCoinsViewModel>() {

    private val fetchLatestModelsHelper: FetchLatestModelsHelper = mock()
    override val viewModel = LatestCoinsViewModel(scope, fetchLatestModelsHelper)

    @Test
    fun `when success on initialization should return the expected states`() {
        runBlockingTest {
            whenever(fetchLatestModelsHelper()).thenReturn(listOf(latestCoin))
            val expectedStates = listOf(
                LatestCoinsState.Uninitialized,
                LatestCoinsState.Loading,
                LatestCoinsState.Loaded(listOf(latestCoin))
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
    fun `when error on initialization should return the expected states`() {
        runBlockingTest {
            whenever(fetchLatestModelsHelper()).thenReturn(emptyList())
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
            whenever(fetchLatestModelsHelper()).thenReturn(emptyList())
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