package com.msd.coin_details

import com.msd.coin_details.mapper.toDomain
import com.msd.coin_details.model.CoinDetailDomain
import com.msd.coin_details.network.CoinDetailRemote
import javax.inject.Inject

class CoinDetailRepository @Inject constructor(
    private val coinDetailRemote: CoinDetailRemote
) : ICoinDetailsRepository {

    override suspend fun fetchCoinDetail(symbol: String): CoinDetailDomain =
        coinDetailRemote.fetchCoinDetailRemote(symbol)!!.toDomain()
}
