package com.msd117.cryptocompose.latest.domain

import com.msd117.cryptocompose.latest.data.LatestCoinsRepository
import com.msd117.cryptocompose.latest.domain.mapper.toDomain
import com.msd117.cryptocompose.latest.domain.model.LatestCoinDomain
import javax.inject.Inject

class FetchLatestCoinsUseCase @Inject constructor(
    private val latestCoinsRepository: LatestCoinsRepository
) {

    suspend operator fun invoke(start: Int, limit: Int, sort: String): List<LatestCoinDomain> =
        latestCoinsRepository.fetchLatest(start, limit, sort).data?.toDomain().orEmpty()
}
