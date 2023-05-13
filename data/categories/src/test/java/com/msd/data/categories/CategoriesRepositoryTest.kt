package com.msd.data.categories

import com.msd.data.categories.TestDataBuilder.buildCategoriesResponse
import com.msd.data.categories.TestDataBuilder.buildCategoryDomain
import com.msd.data.categories.network.CategoriesRemote
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CategoriesRepositoryTest : CoroutineTest() {

    private val categoriesRemote: CategoriesRemote = mock()
    private val repository = CategoriesRepository(categoriesRemote)

    @Test
    fun `when fetching categories should return the expected models`() = runTest {
        val categoriesResponse = buildCategoriesResponse()
        val expectedResult = listOf(buildCategoryDomain())
        whenever(categoriesRemote.fetchRemoteCategories(0, 0)).thenReturn(categoriesResponse)

        val result = repository.fetchCategories(0, 0)

        assert(result == expectedResult)
        verify(categoriesRemote).fetchRemoteCategories(0, 0)
        verifyNoMoreInteractions(categoriesRemote)
    }
}