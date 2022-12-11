package com.msd.data.latest_coins

import com.msd.data.latest_coins.mapper.LatestDomainMapper.toDomain
import com.msd.data.latest_coins.network.LatestCoinsRemote
import com.msd.domain.latest_coins_list.ILatestCoinsRepository
import javax.inject.Inject

class LatestCoinsRepository @Inject constructor(
    private val latestCoinsRemote: LatestCoinsRemote
) : ILatestCoinsRepository {

    override suspend fun fetchLatest(start: Int, limit: Int, sort: String) =
        latestCoinsRemote.fetchRemoteLatest(start, limit, sort).coinDataList?.toDomain().orEmpty()
}
