package com.msd117.cryptocompose.presentation.latest.presenter

import com.msd117.cryptocompose.presentation.latest.helper.FetchLatestModelsHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LatestCoinsViewModelTest {

    private val scope = TestCoroutineScope()
    private val fetchLatestModelsHelper: FetchLatestModelsHelper = mock()

    private val viewModel = LatestCoinsViewModel(scope, fetchLatestModelsHelper)

    @Test
    fun `when fetching latest coins successfully should emit the expected state`() {
        runBlocking { whenever(fetchLatestModelsHelper()).thenReturn(emptyList()) }
        val expectedStates = listOf(
            LatestCoinsState.Loading,
            LatestCoinsState.Error
        )

        viewModel.fetchLatestCoins()
        val states = mutableListOf<LatestCoinsState>()
        runBlocking { viewModel.getState().toCollection(states) }

        assert(states == expectedStates)
    }
}