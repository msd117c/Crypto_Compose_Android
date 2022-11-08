package com.msd117.cryptocompose.detail.presenter.helper

import com.msd117.cryptocompose.detail.domain.FetchCoinDetailUseCase
import com.msd117.cryptocompose.detail.presenter.mapper.toPresentation
import com.msd117.cryptocompose.detail.presenter.model.CoinDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class FetchCoinDetailHelper @Inject constructor(
    private val fetchCoinDetailUseCase: FetchCoinDetailUseCase
) {

    suspend operator fun invoke(symbol: String): CoinDetail = withContext(Dispatchers.IO) {
        fetchCoinDetailUseCase(symbol)?.toPresentation() ?: throw IOException()
    }
}
