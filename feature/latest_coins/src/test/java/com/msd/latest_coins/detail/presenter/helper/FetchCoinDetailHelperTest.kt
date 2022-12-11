package com.msd.latest_coins.detail.presenter.helper

import com.msd.domain.coin_details.FetchCoinDetailUseCase
import com.msd.latest_coins.common.TestDataBuilder.buildCoinDetail
import com.msd.latest_coins.common.TestDataBuilder.buildCoinDetailDomain
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import java.util.*

@ExperimentalCoroutinesApi
class FetchCoinDetailHelperTest : CoroutineTest() {

    private val fetchCoinDetailUseCase: FetchCoinDetailUseCase = mock()
    private val helper = FetchCoinDetailHelper(fetchCoinDetailUseCase)

    init {
        Locale.setDefault(Locale.UK)
    }

    @Test
    fun `when fetching coin details should invoke the use case and map the result`() = runTest {
        val symbol = "Symbol"
        val coinDetailDomain = buildCoinDetailDomain()
        val coinDetail = buildCoinDetail()
        whenever(fetchCoinDetailUseCase(symbol)).thenReturn(coinDetailDomain)

        val result = helper(symbol)

        assert(result == coinDetail)
        verify(fetchCoinDetailUseCase).invoke(symbol)
        verifyNoMoreInteractions(fetchCoinDetailUseCase)
    }
}
