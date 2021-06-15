package com.msd117.cryptocompose.domain.usecase.connection

import com.msd117.cryptocompose.domain.provider.connection.NetworkCapabilitiesProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsConnectionAvailableUseCase @Inject constructor(
    private val networkCapabilitiesProvider: NetworkCapabilitiesProvider
) {

    operator fun invoke(): Flow<Boolean> = networkCapabilitiesProvider.getConnectedState()
}