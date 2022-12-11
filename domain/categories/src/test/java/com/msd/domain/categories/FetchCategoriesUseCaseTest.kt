package com.msd.domain.categories

import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FetchCategoriesUseCaseTest : CoroutineTest() {

    private val categoriesRepository: ICategoriesRepository = mock()
    private val useCase = FetchCategoriesUseCase(categoriesRepository)

    @Test
    fun `when fetching categories should invoke the repository`() = runTest {
        whenever(categoriesRepository.fetchCategories(0, 0)).thenReturn(mock())

        useCase(0, 0)

        verify(categoriesRepository).fetchCategories(0, 0)
        verifyNoMoreInteractions(categoriesRepository)
    }
}