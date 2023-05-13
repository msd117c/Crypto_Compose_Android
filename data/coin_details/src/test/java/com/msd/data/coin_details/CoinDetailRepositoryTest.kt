package com.msd.data.coin_details

import com.msd.data.coin_details.TestDataBuilder.buildCoinDetail
import com.msd.data.coin_details.TestDataBuilder.buildCoinDetailDomain
import com.msd.data.coin_details.network.CoinDetailRemote
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CoinDetailRepositoryTest : CoroutineTest() {

    private val coinDetailRemote: CoinDetailRemote = mock()
    private val repository = CoinDetailRepository(coinDetailRemote)

    @Test
    fun `when fetching coin details should invoke the remote`() = runTest {
        val coinDetail = buildCoinDetail()
        val symbol = "Symbol"
        val expectedResult = buildCoinDetailDomain()
        whenever(coinDetailRemote.fetchCoinDetailRemote(symbol)).thenReturn(coinDetail)

        val result = repository.fetchCoinDetail(symbol)

        assert(result == expectedResult)
        verify(coinDetailRemote).fetchCoinDetailRemote(symbol)
        verifyNoMoreInteractions(coinDetailRemote)
    }
}