package com.msd.domain.coin_details

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchCoinDetailUseCase @Inject constructor(
    private val coinDetailRepository: ICoinDetailsRepository
) {

    suspend operator fun invoke(symbol: String) = withContext(Dispatchers.IO) {
        coinDetailRepository.fetchCoinDetail(symbol)
    }
}
