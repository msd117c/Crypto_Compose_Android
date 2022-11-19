package com.msd.latest_coins_list

import com.msd.latest_coins_list.model.LatestCoinDomain

interface ILatestCoinsRepository {
    suspend fun fetchLatest(start: Int, limit: Int, sort: String): List<LatestCoinDomain>
}