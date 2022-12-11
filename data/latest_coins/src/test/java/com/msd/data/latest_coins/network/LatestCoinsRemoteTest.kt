package com.msd.data.latest_coins.network

import com.msd.data.latest_coins.model.LatestCoinsResponse
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
class LatestCoinsRemoteTest : CoroutineTest() {

    private val latestCoinsResponse: LatestCoinsResponse = mock()
    private val latestCoinsNetwork: LatestCoinsNetwork = mock()
    private val retrofitClient: Retrofit = mock()
    private val remote = LatestCoinsRemote(retrofitClient)

    @Test
    fun `when fetching from remote should invoke retrofit and the expected service`() = runTest {
        whenever(latestCoinsNetwork.fetchLatestCoins(0, 0, "sort")).thenReturn(latestCoinsResponse)
        whenever(retrofitClient.create(LatestCoinsNetwork::class.java)).thenReturn(
            latestCoinsNetwork
        )

        val result = remote.fetchRemoteLatest(0, 0, "sort")

        assert(result == latestCoinsResponse)
        verify(retrofitClient).create(LatestCoinsNetwork::class.java)
        verifyNoMoreInteractions(retrofitClient)
        verify(latestCoinsNetwork).fetchLatestCoins(0, 0, "sort")
        verifyNoMoreInteractions(latestCoinsNetwork)
    }
}