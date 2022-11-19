package com.msd.categories.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.msd.categories.presenter.CategoriesState
import com.msd.categories.presenter.CategoriesViewModel
import com.msd.categories.presenter.initialState

@Composable
fun CategoriesView(viewModel: CategoriesViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)
    viewModel.initialize()

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is CategoriesState.Loading -> CategoriesLoadingView()
            is CategoriesState.Error -> CategoriesErrorView()
            is CategoriesState.Loaded -> CategoriesLoadedView(state.categories)
            is CategoriesState.Uninitialized -> Unit
        }
    }
}
