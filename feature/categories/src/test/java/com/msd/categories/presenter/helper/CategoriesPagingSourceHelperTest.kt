package com.msd.categories.presenter.helper

import androidx.paging.PagingSource
import com.msd.domain.categories.FetchCategoriesUseCase
import com.msd.categories.TestDataBuilder.buildCategory
import com.msd.categories.TestDataBuilder.buildCategoryDomain
import com.msd.categories.presenter.model.Category
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CategoriesPagingSourceHelperTest : CoroutineTest() {

    private val fetchCategoriesUseCase: FetchCategoriesUseCase = mock()

    @Test
    fun `when loading successfully should return the expected data`() = runTest {
        val categoriesDomain = listOf(buildCategoryDomain())
        val expectedCategories = listOf(buildCategory())
        val expected = PagingSource.LoadResult.Page(
            data = expectedCategories,
            prevKey = null,
            nextKey = 2
        )
        whenever(fetchCategoriesUseCase(any(), any())).thenReturn(categoriesDomain)
        val pagingSource = CategoriesPagingSourceHelper(fetchCategoriesUseCase)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        assert(result == expected)
    }

    @Test
    fun `when loading with errors should return the expected error`() = runTest {
        val exception = RuntimeException()
        val expected = PagingSource.LoadResult.Error<Int, Category>(exception)
        whenever(fetchCategoriesUseCase(any(), any())).thenThrow(exception)
        val pagingSource = CategoriesPagingSourceHelper(fetchCategoriesUseCase)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assert(result == expected)
    }
}
