package com.msd.latest_coins.detail.presenter.helper

import com.msd.coin_details.FetchCoinDetailUseCase
import com.msd.latest_coins.detail.presenter.mapper.toPresentation
import com.msd.latest_coins.detail.presenter.model.CoinDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchCoinDetailHelper @Inject constructor(
    private val fetchCoinDetailUseCase: FetchCoinDetailUseCase
) {

    suspend operator fun invoke(symbol: String): CoinDetail = withContext(Dispatchers.IO) {
        fetchCoinDetailUseCase(symbol).toPresentation()
    }
}
