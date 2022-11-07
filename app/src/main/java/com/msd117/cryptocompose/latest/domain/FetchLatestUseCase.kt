package com.msd117.cryptocompose.latest.domain

import com.msd117.cryptocompose.latest.data.LatestRepository
import com.msd117.cryptocompose.latest.domain.mapper.toDomain
import com.msd117.cryptocompose.latest.domain.model.LatestCoinDomain
import javax.inject.Inject

class FetchLatestUseCase @Inject constructor(private val latestRepository: LatestRepository) {

    suspend operator fun invoke(start: Int, limit: Int, sort: String): List<LatestCoinDomain> =
        latestRepository.fetchLatest(start, limit, sort).data?.toDomain().orEmpty()
}
