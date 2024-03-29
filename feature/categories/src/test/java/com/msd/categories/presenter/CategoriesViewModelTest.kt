package com.msd.categories.presenter

import androidx.paging.PagingData
import com.msd.categories.TestDataBuilder.buildCategory
import com.msd.categories.presenter.CategoriesState.*
import com.msd.categories.presenter.helper.FetchCategoriesHelper
import com.msd.core.presentation.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Ignore
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CategoriesViewModelTest : ViewModelTest<CategoriesState, CategoriesViewModel>() {

    private val fetchCategoriesHelper: FetchCategoriesHelper = mock()
    override val viewModel = CategoriesViewModel(fetchCategoriesHelper)

    @Test
    fun `when initializing successfully should return the expected states`() = test(
        conditions = {
            val pagingData = flowOf(PagingData.from(listOf(buildCategory())))
            whenever(fetchCategoriesHelper()).thenReturn(pagingData)
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results.take(2) == listOf(Uninitialized, Loading))
            assert(results.last() is Loaded)
            verify(fetchCategoriesHelper, only()).invoke()
        }
    )

    @Test
    fun `when initializing with errors should return the expected states`() = test(
        conditions = {
            whenever(fetchCategoriesHelper()).thenThrow(RuntimeException())
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Uninitialized, Loading, Error))
            verify(fetchCategoriesHelper, only()).invoke()
        }
    )

    @Test
    @Ignore
    fun `when already initialized should not initialize again`() = test(
        conditions = {
            whenever(fetchCategoriesHelper()).thenThrow(RuntimeException())
            viewModel.initialize()
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Error))
            verify(fetchCategoriesHelper, only()).invoke()
        }
    )
}