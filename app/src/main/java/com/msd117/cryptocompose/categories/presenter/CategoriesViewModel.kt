package com.msd117.cryptocompose.categories.presenter

import androidx.paging.cachedIn
import com.msd117.cryptocompose.categories.presenter.helper.FetchCategoriesHelper
import com.msd117.cryptocompose.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    coroutineScope: CoroutineScope?,
    private val fetchCategoriesHelper: FetchCategoriesHelper
) : BaseViewModel<CategoriesState>(coroutineScope) {

    override val state: MutableStateFlow<CategoriesState> = MutableStateFlow(initialState)

    fun initialize() {
        if (state.value !is CategoriesState.Uninitialized) return

        scope.launch {
            state.value = CategoriesState.Loading
            try {
                val categories = fetchCategoriesHelper().cachedIn(scope)
                state.value = CategoriesState.Loaded(categories)
            } catch (exception: Exception) {
                state.value = CategoriesState.Error
            }
        }
    }
}
