package com.msd117.cryptocompose.presentation.main.presenter

import com.msd117.cryptocompose.domain.usecase.connection.IsConnectionAvailableUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testScope = TestCoroutineScope()
    private val isConnectionAvailableUseCase: IsConnectionAvailableUseCase = mock()

    private val viewModel = MainViewModel(testScope, isConnectionAvailableUseCase)

    private val expectedStates = listOf(MainState.Uninitialized)

    @Test
    fun `when initializing with connection should emit the state with isConnected as true`() =
        runBlockingTest {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(true))
            val expectedStates = expectedStates + listOf(MainState.Loaded(isConnected = true))
            val states = mutableListOf<MainState>()
            val job = launch { viewModel.getState().toList(states) }

            viewModel.initialize()

            assert(states == expectedStates)
            job.cancel()
        }

    @Test
    fun `when initializing without connection should emit the state with isConnected as true`() =
        runBlockingTest {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(false))
            val expectedStates = expectedStates + listOf(MainState.Loaded(isConnected = false))
            val states = mutableListOf<MainState>()
            val job = launch { viewModel.getState().toList(states) }

            viewModel.initialize()

            assert(states == expectedStates)
            job.cancel()
        }

    @Test
    fun `when state is already initialized should not initialize again`() =
        runBlockingTest {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(false))
            viewModel.initialize()
            val expectedStates = listOf(MainState.Loaded(isConnected = false))
            val states = mutableListOf<MainState>()
            val job = launch { viewModel.getState().toList(states) }

            viewModel.initialize()

            assert(states == expectedStates)
            job.cancel()
        }
}