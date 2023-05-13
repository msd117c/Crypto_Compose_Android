package com.msd.data.categories.network

import com.msd.data.categories.model.CategoriesResponse
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CategoriesRemoteTest : CoroutineTest() {

    private val categoriesNetwork: CategoriesNetwork = mock()
    private val remote = CategoriesRemote(categoriesNetwork)

    @Test
    fun `when fetching categories should invoke the network`() = runTest {
        val response: CategoriesResponse = mock()
        whenever(categoriesNetwork.fetchCategories(0, 0)).thenReturn(response)

        val result = remote.fetchRemoteCategories(0, 0)

        assert(result == response)
        verify(categoriesNetwork).fetchCategories(0, 0)
        verifyNoMoreInteractions(categoriesNetwork)
    }
}