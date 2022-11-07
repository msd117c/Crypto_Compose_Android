package com.msd117.cryptocompose.detail.data

import com.msd117.cryptocompose.detail.data.model.Data
import com.msd117.cryptocompose.detail.data.network.InfoRemote
import javax.inject.Inject

class InfoRepository @Inject constructor(private val infoRemote: InfoRemote) {

    suspend fun fetchInfo(symbol: String): Data? = infoRemote.fetchRemoteInfo(symbol)
}
