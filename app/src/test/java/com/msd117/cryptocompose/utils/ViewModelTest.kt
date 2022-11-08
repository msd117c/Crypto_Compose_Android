package com.msd117.cryptocompose.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope

abstract class ViewModelTest<V : ViewModel> {

    @ExperimentalCoroutinesApi
    protected val scope = TestScope()

    protected abstract val viewModel: V
}
