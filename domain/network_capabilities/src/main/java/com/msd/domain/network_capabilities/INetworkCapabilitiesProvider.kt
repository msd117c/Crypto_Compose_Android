package com.msd.domain.network_capabilities

import kotlinx.coroutines.flow.Flow

interface INetworkCapabilitiesProvider {
    fun getConnectedState(): Flow<Boolean>
}