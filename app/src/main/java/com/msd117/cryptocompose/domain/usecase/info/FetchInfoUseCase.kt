package com.msd117.cryptocompose.domain.usecase.info

import com.msd117.cryptocompose.data.repository.info.InfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchInfoUseCase @Inject constructor(private val infoRepository: InfoRepository) {

    suspend operator fun invoke(symbol: String) = withContext(Dispatchers.IO) {
        infoRepository.fetchInfo(symbol)
    }
}
