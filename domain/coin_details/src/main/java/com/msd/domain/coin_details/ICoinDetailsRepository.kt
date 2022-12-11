package com.msd.domain.coin_details

import com.msd.domain.coin_details.model.CoinDetailDomain

interface ICoinDetailsRepository {
    suspend fun fetchCoinDetail(symbol: String): CoinDetailDomain
}