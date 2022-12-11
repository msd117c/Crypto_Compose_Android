package com.msd.core.presentation

import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
abstract class ViewModelTest<S : State, V : BaseViewModel<S>> : CoroutineTest() {

    protected abstract val viewModel: V
    private val results = mutableListOf<S>()

    fun test(
        conditions: suspend TestScope.() -> Unit,
        execution: () -> Unit,
        assertions: suspend TestScope.(MutableList<S>) -> Unit
    ) = runTest {
        conditions()
        advanceUntilIdle()

        val job = launch {
            viewModel.getState()
                .onEach { results.add(it) }
                .launchIn(TestScope(UnconfinedTestDispatcher()))
        }
        execution()
        advanceUntilIdle()

        assertions(results)
        results.clear()
        job.cancel()
    }
}