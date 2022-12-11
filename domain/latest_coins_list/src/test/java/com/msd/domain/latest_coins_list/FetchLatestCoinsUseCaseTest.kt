package com.msd.domain.latest_coins_list

import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FetchLatestCoinsUseCaseTest : CoroutineTest() {

    private val latestCoinsRepository: ILatestCoinsRepository = mock()
    private val useCase = FetchLatestCoinsUseCase(latestCoinsRepository)

    @Test
    fun `when fetching latest coins should invoke the repository`() = runTest {
        whenever(latestCoinsRepository.fetchLatest(0, 0, "sort")).thenReturn(emptyList())

        useCase(0, 0, "sort")

        verify(latestCoinsRepository).fetchLatest(0, 0, "sort")
    }
}