package com.msd.data.coin_details

import com.msd.data.coin_details.mapper.CoinDetailDomainMapper.toDomain
import com.msd.domain.coin_details.model.CoinDetailDomain
import com.msd.data.coin_details.network.CoinDetailRemote
import com.msd.domain.coin_details.ICoinDetailsRepository
import javax.inject.Inject

class CoinDetailRepository @Inject constructor(
    private val coinDetailRemote: CoinDetailRemote
) : ICoinDetailsRepository {

    override suspend fun fetchCoinDetail(symbol: String): CoinDetailDomain =
        coinDetailRemote.fetchCoinDetailRemote(symbol)!!.toDomain()
}
