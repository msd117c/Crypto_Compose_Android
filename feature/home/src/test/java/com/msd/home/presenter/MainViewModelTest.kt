package com.msd.home.presenter

import com.msd.domain.network_capabilities.IsConnectionAvailableUseCase
import com.msd.home.presenter.MainState.Loaded
import com.msd.home.presenter.MainState.Uninitialized
import com.msd.unit_test.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelTest : ViewModelTest<MainState, MainViewModel>() {

    private val isConnectionAvailableUseCase: IsConnectionAvailableUseCase = mock()
    override val viewModel = MainViewModel(isConnectionAvailableUseCase)

    @Test
    fun `when initializing with connection should return the expected states`() = test(
        conditions = {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(true))
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Uninitialized, Loaded(true)))
            verify(isConnectionAvailableUseCase, only()).invoke()
        }
    )

    @Test
    fun `when initializing without connection should return the expected states`() = test(
        conditions = {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(false))
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Uninitialized, Loaded(false)))
            verify(isConnectionAvailableUseCase, only()).invoke()
        }
    )

    @Test
    fun `when state is already initialized should not initialize again`() = test(
        conditions = {
            whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(true))
            viewModel.initialize()
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Loaded(true)))
            verify(isConnectionAvailableUseCase, only()).invoke()
        }
    )
}
