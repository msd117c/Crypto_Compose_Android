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

@ExperimentalCoroutinesApi
class LatestCoinsRemoteTest : CoroutineTest() {

    private val latestCoinsResponse: LatestCoinsResponse = mock()
    private val latestCoinsNetwork: LatestCoinsNetwork = mock()
    private val remote = LatestCoinsRemote(latestCoinsNetwork)

    @Test
    fun `when fetching from remote should invoke retrofit and the expected service`() = runTest {
        whenever(latestCoinsNetwork.fetchLatestCoins(0, 0, "sort")).thenReturn(latestCoinsResponse)

        val result = remote.fetchRemoteLatest(0, 0, "sort")

        assert(result == latestCoinsResponse)
        verify(latestCoinsNetwork).fetchLatestCoins(0, 0, "sort")
        verifyNoMoreInteractions(latestCoinsNetwork)
    }
}