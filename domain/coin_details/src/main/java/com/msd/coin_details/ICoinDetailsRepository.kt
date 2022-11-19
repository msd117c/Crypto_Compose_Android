package com.msd.coin_details

import com.msd.coin_details.model.CoinDetailDomain

interface ICoinDetailsRepository {
    suspend fun fetchCoinDetail(symbol: String): CoinDetailDomain
}