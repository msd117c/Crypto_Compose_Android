package com.msd117.cryptocompose.detail.domain

import com.msd117.cryptocompose.detail.data.InfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchInfoUseCase @Inject constructor(private val infoRepository: InfoRepository) {

    suspend operator fun invoke(symbol: String) = withContext(Dispatchers.IO) {
        infoRepository.fetchInfo(symbol)
    }
}
