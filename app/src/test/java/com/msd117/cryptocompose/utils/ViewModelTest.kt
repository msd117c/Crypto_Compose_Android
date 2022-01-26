package com.msd117.cryptocompose.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope

abstract class ViewModelTest<V : ViewModel> {

    @ExperimentalCoroutinesApi
    protected val scope = TestCoroutineScope()

    protected abstract val viewModel: V
}