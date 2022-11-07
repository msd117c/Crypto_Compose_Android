package com.msd117.cryptocompose.main.domain

import com.msd117.cryptocompose.main.domain.provider.NetworkCapabilitiesProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsConnectionAvailableUseCase @Inject constructor(
    private val networkCapabilitiesProvider: NetworkCapabilitiesProvider
) {

    operator fun invoke(): Flow<Boolean> = networkCapabilitiesProvider.getConnectedState()
}
