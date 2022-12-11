package com.msd.domain.coin_details

import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FetchCoinDetailUseCaseTest : CoroutineTest() {

    private val coinDetailsRepository: ICoinDetailsRepository = mock()
    private val useCase = FetchCoinDetailUseCase(coinDetailsRepository)

    @Test
    fun `when fetching coin details should invoke the repository`() = runTest {
        whenever(coinDetailsRepository.fetchCoinDetail("Symbol")).thenReturn(mock())

        useCase("Symbol")

        verify(coinDetailsRepository).fetchCoinDetail("Symbol")
        verifyNoMoreInteractions(coinDetailsRepository)
    }
}