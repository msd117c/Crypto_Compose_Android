package com.msd117.cryptocompose.detail.data

import com.msd117.cryptocompose.detail.data.model.CoinDetail
import com.msd117.cryptocompose.detail.data.network.CoinDetailRemote
import javax.inject.Inject

class CoinDetailRepository @Inject constructor(private val coinDetailRemote: CoinDetailRemote) {

    suspend fun fetchCoinDetail(symbol: String): CoinDetail? =
        coinDetailRemote.fetchCoinDetailRemote(symbol)
}
