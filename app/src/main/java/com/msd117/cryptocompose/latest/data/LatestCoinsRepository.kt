package com.msd117.cryptocompose.latest.data

import com.msd117.cryptocompose.latest.data.network.LatestCoinsRemote
import javax.inject.Inject

class LatestCoinsRepository @Inject constructor(private val latestCoinsRemote: LatestCoinsRemote) {

    suspend fun fetchLatest(start: Int, limit: Int, sort: String) =
        latestCoinsRemote.fetchRemoteLatest(start, limit, sort)
}
