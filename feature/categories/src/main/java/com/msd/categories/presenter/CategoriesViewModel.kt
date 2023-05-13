package com.msd.categories.presenter

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.msd.categories.presenter.helper.FetchCategoriesHelper
import com.msd.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val fetchCategoriesHelper: FetchCategoriesHelper
) : BaseViewModel<CategoriesState>() {

    override val state: MutableStateFlow<CategoriesState> = MutableStateFlow(initialState)

    override fun initialize() {
        super.initialize()

        viewModelScope.launch {
            state.value = CategoriesState.Loading
            try {
                val categories = fetchCategoriesHelper().cachedIn(viewModelScope)
                state.value = CategoriesState.Loaded(categories)
            } catch (exception: Exception) {
                state.value = CategoriesState.Error
            }
        }
    }
}
