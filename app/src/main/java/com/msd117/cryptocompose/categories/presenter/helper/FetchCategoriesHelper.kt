package com.msd117.cryptocompose.categories.presenter.helper

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msd117.cryptocompose.categories.domain.FetchCategoriesUseCase
import com.msd117.cryptocompose.categories.presenter.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCategoriesHelper @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) {

    operator fun invoke(): Flow<PagingData<Category>> {
        return Pager(PagingConfig(pageSize = 20)) {
            CategoriesPagingSourceHelper(fetchCategoriesUseCase)
        }.flow
    }
}
