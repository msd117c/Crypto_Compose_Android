package com.msd117.cryptocompose.latest.data

import com.msd117.cryptocompose.latest.data.network.LatestRemote
import javax.inject.Inject

class LatestRepository @Inject constructor(private val latestRemote: LatestRemote) {

    suspend fun fetchLatest(start: Int, limit: Int, sort: String) =
        latestRemote.fetchRemoteLatest(start, limit, sort)
}
