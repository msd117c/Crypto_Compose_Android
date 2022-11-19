package com.msd.latest_coins_list

import com.msd.latest_coins_list.model.LatestCoinDomain
import javax.inject.Inject

class FetchLatestCoinsUseCase @Inject constructor(
    private val latestCoinsRepository: ILatestCoinsRepository
) {

    suspend operator fun invoke(start: Int, limit: Int, sort: String): List<LatestCoinDomain> =
        latestCoinsRepository.fetchLatest(start, limit, sort)
}
