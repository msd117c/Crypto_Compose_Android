package com.msd117.cryptocompose.presentation.latest.presenter

import com.msd117.cryptocompose.presentation.latest.helper.FetchLatestModelsHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LatestCoinsViewModelTest {

    private val scope = TestCoroutineScope()
    private val fetchLatestModelsHelper: FetchLatestModelsHelper = mock()

    private val viewModel = LatestCoinsViewModel(scope, fetchLatestModelsHelper)

    @Test
    fun `when fetching latest coins successfully should emit the expected state`() =
        runBlockingTest {
            whenever(fetchLatestModelsHelper()).thenReturn(emptyList())
            val expectedStates = listOf(
                LatestCoinsState.Uninitialized,
                LatestCoinsState.Loading,
                LatestCoinsState.Error
            )
            val states = mutableListOf<LatestCoinsState>()
            val job = launch { viewModel.getState().toList(states) }

            viewModel.initialize()

            assert(states == expectedStates)
            job.cancel()
        }

    @Test
    fun `when already initialized should not initialize again`() =
        runBlockingTest {
            whenever(fetchLatestModelsHelper()).thenReturn(emptyList())
            viewModel.initialize()
            val expectedStates = listOf(LatestCoinsState.Error)
            val states = mutableListOf<LatestCoinsState>()
            val job = launch { viewModel.getState().toList(states) }

            viewModel.initialize()

            assert(states == expectedStates)
            job.cancel()
        }
}