package com.msd117.cryptocompose.presentation.main.domain

import com.msd117.cryptocompose.domain.connection.ConnectivityInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsConnectionAvailableUseCase @Inject constructor(
    private val connectivityInformation: ConnectivityInformation
) {

    operator fun invoke(): Flow<Boolean> = connectivityInformation.getConnectedState()
}