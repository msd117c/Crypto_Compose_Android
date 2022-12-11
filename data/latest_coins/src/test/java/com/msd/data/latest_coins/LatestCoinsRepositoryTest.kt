package com.msd.data.latest_coins

import com.msd.data.latest_coins.network.LatestCoinsRemote
import com.msd.unit_test.CoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LatestCoinsRepositoryTest : CoroutineTest() {

    private val latestCoinsRemote: LatestCoinsRemote = mock()
    private val repository = LatestCoinsRepository(latestCoinsRemote)

    @Test
    fun `when fetching coins from remote should invoke the remote`() = runTest {
        whenever(latestCoinsRemote.fetchRemoteLatest(0, 0, "sort")).thenReturn(mock())

        repository.fetchLatest(0, 0, "sort")

        verify(latestCoinsRemote).fetchRemoteLatest(0, 0, "sort")
        verifyNoMoreInteractions(latestCoinsRemote)
    }
}