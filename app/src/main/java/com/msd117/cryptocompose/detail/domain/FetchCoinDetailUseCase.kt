package com.msd117.cryptocompose.detail.domain

import com.msd117.cryptocompose.detail.data.CoinDetailRepository
import com.msd117.cryptocompose.detail.domain.mapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchCoinDetailUseCase @Inject constructor(
    private val coinDetailRepository: CoinDetailRepository
) {

    suspend operator fun invoke(symbol: String) = withContext(Dispatchers.IO) {
        coinDetailRepository.fetchCoinDetail(symbol)?.toDomain()
    }
}
