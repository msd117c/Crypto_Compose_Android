package com.msd.data.coin_details.network

import com.msd.data.coin_details.TestDataBuilder.buildCoinDetail
import com.msd.data.coin_details.TestDataBuilder.coinDetailJson
import com.msd.data.coin_details.TestDataBuilder.coinDetailResponseJson
import com.msd.data.coin_details.model.CoinDetail
import com.msd.unit_test.CoroutineTest
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody.create
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CoinDetailRemoteTest : CoroutineTest() {

    private val coinDetailNetwork: CoinDetailNetwork = mock()
    private val jsonAdapter: JsonAdapter<CoinDetail> = mock()
    private val remote = CoinDetailRemote(coinDetailNetwork, jsonAdapter)

    @Test
    fun `when fetching coin details should invoke the network`() = runTest {
        val symbol = "Symbol"
        val expectedResult = buildCoinDetail()
        val responseBody = create(MediaType.get("application/json"), coinDetailResponseJson)
        val coinDetailString = coinDetailJson.replace("\n", "").replace(" ", "")
        whenever(coinDetailNetwork.fetchCoinDetailRemote(symbol)).thenReturn(responseBody)
        whenever(jsonAdapter.fromJson(coinDetailString)).thenReturn(expectedResult)

        val result = remote.fetchCoinDetailRemote(symbol)

        assert(result == expectedResult)
        verify(coinDetailNetwork).fetchCoinDetailRemote(symbol)
        verifyNoMoreInteractions(coinDetailNetwork)
        verify(jsonAdapter).fromJson(coinDetailString)
        verifyNoMoreInteractions(jsonAdapter)
    }
}