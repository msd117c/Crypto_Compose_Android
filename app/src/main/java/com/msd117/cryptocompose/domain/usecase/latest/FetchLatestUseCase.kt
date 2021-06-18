package com.msd117.cryptocompose.domain.usecase.latest

import com.msd117.cryptocompose.data.model.latest.LatestResponse
import com.msd117.cryptocompose.data.repository.latest.LatestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchLatestUseCase @Inject constructor(private val latestRepository: LatestRepository) {

    suspend operator fun invoke(): LatestResponse = withContext(Dispatchers.IO) {
        latestRepository.fetchLatest()
    }
}