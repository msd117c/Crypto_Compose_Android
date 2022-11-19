package com.msd.domain.network_capabilities

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsConnectionAvailableUseCase @Inject constructor(
    private val networkCapabilitiesProvider: INetworkCapabilitiesProvider
) {

    operator fun invoke(): Flow<Boolean> = networkCapabilitiesProvider.getConnectedState()
}
