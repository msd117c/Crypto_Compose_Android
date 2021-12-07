package com.msd117.cryptocompose.presentation.main.presenter

import com.msd117.cryptocompose.domain.usecase.connection.IsConnectionAvailableUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testScope = TestCoroutineScope()
    private val isConnectionAvailableUseCase: IsConnectionAvailableUseCase = mock()

    private val viewModel = MainViewModel(testScope, isConnectionAvailableUseCase)

    @Test
    fun `when initializing with connection should emit the state with isConnected as true`() {
        whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(true))
        val expectedState = runBlocking { viewModel.getState().first().copy(isConnected = true) }

        viewModel.initialize()

        val state = runBlocking { viewModel.getState().first() }
        assert(state == expectedState)
    }

    @Test
    fun `when initializing without connection should emit the state with isConnected as true`() {
        whenever(isConnectionAvailableUseCase()).thenReturn(flowOf(false))
        val expectedState = runBlocking { viewModel.getState().first().copy(isConnected = false) }

        viewModel.initialize()

        val state = runBlocking { viewModel.getState().first() }
        assert(state == expectedState)
    }
}