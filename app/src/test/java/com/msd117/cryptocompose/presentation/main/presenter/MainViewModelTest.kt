package com.msd117.cryptocompose.presentation.main.presenter

import com.msd117.cryptocompose.main.domain.IsConnectionAvailableUseCase
import com.msd117.cryptocompose.main.presenter.MainState
import com.msd117.cryptocompose.main.presenter.MainViewModel
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

@ExperimentalCoroutinesApi
class MainViewModelTest : ViewModelTest<MainViewModel>() {

    private val isConnectionAvailableUseCase: IsConnectionAvailableUseCase = mock()
    override val viewModel = MainViewModel(scope, isConnectionAvailableUseCase)

    @Test
    fun `when initializing with connection should return the expected states`() {
        runBlockingTest {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(true))
            val expectedStates = listOf(
                MainState.Uninitialized,
                MainState.Loaded(isConnected = true)
            )
            val states = mutableListOf<MainState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(isConnectionAvailableUseCase, only()).invoke()
                assert(expectedStates == states)
                cancel()
            }
        }
    }

    @Test
    fun `when initializing without connection should return the expected states`() {
        runBlockingTest {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(false))
            val expectedStates = listOf(
                MainState.Uninitialized,
                MainState.Loaded(isConnected = false)
            )
            val states = mutableListOf<MainState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(isConnectionAvailableUseCase, only()).invoke()
                assert(expectedStates == states)
                cancel()
            }
        }
    }

    @Test
    fun `when state is already initialized should not initialize again`() =
        runBlockingTest {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(false))
            viewModel.initialize()
            val expectedStates = listOf(MainState.Loaded(isConnected = false))
            val states = mutableListOf<MainState>()
            launch { viewModel.getState().toList(states) }.apply {

                viewModel.initialize()

                verify(isConnectionAvailableUseCase, only()).invoke()
                assert(states == expectedStates)
                cancel()
            }

        }
}
