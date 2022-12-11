package com.msd.domain.latest_coins_list

import com.msd.domain.latest_coins_list.model.LatestCoinDomain

interface ILatestCoinsRepository {
    suspend fun fetchLatest(start: Int, limit: Int, sort: String): List<LatestCoinDomain>
}