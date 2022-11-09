package com.msd117.cryptocompose.categories.presenter

import androidx.paging.PagingData
import com.msd117.cryptocompose.categories.presenter.model.Category
import com.msd117.cryptocompose.utils.State
import kotlinx.coroutines.flow.Flow

sealed class CategoriesState : State {

    object Uninitialized : CategoriesState()
    object Loading : CategoriesState()
    data class Loaded(val categories: Flow<PagingData<Category>>) : CategoriesState()
    object Error : CategoriesState()
}

val initialState = CategoriesState.Uninitialized