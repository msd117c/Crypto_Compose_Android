package com.msd117.cryptocompose.categories.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.msd117.cryptocompose.categories.presenter.CategoriesState
import com.msd117.cryptocompose.categories.presenter.CategoriesViewModel
import com.msd117.cryptocompose.categories.presenter.initialState

@Composable
fun CategoriesView(viewModel: CategoriesViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)
    viewModel.initialize()

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        when (state) {
            is CategoriesState.Loading -> CategoriesLoadingView()
            is CategoriesState.Error -> CategoriesErrorView()
            is CategoriesState.Loaded -> CategoriesLoadedView()
            is CategoriesState.Uninitialized -> Unit
        }
    }
}
