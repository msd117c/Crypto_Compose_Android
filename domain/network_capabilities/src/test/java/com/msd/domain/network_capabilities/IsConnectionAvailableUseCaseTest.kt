package com.msd.domain.network_capabilities

import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify

class IsConnectionAvailableUseCaseTest {

    private val capabilitiesProvider: INetworkCapabilitiesProvider = mock {
        on { getConnectedState() } doReturn mock()
    }
    private val isConnectionAvailableUseCase = IsConnectionAvailableUseCase(capabilitiesProvider)

    @Test
    fun `when getting connection availability should invoke the provider`() {

        isConnectionAvailableUseCase()

        verify(capabilitiesProvider, only()).getConnectedState()
    }
}