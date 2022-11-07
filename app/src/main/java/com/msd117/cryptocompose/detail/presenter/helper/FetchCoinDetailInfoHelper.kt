package com.msd117.cryptocompose.detail.presenter.helper

import com.msd117.cryptocompose.detail.domain.FetchInfoUseCase
import com.msd117.cryptocompose.detail.presenter.mapper.toPresentation
import com.msd117.cryptocompose.detail.presenter.model.CoinDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class FetchCoinDetailInfoHelper @Inject constructor(private val fetchInfoUseCase: FetchInfoUseCase) {

    suspend operator fun invoke(symbol: String): CoinDetail = withContext(Dispatchers.IO) {
        fetchInfoUseCase(symbol)?.toPresentation() ?: throw IOException()
    }
}
