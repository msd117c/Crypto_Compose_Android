package com.msd.categories.presenter.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msd.domain.categories.FetchCategoriesUseCase
import com.msd.categories.presenter.mapper.CategoryMapper.toPresentation
import com.msd.categories.presenter.model.Category

class CategoriesPagingSourceHelper(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : PagingSource<Int, Category>() {

    override fun getRefreshKey(state: PagingState<Int, Category>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Category> {
        return try {
            val nextPage = (params.key ?: 1)
            val start = (nextPage.minus(1)).times(params.loadSize).plus(1)
            val categories = fetchCategoriesUseCase(start, limit = params.loadSize).toPresentation()
            LoadResult.Page(
                data = categories,
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (categories.isEmpty()) null else nextPage.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
