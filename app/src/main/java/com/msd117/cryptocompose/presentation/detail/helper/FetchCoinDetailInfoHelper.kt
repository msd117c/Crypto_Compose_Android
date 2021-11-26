package com.msd117.cryptocompose.presentation.detail.helper

import com.msd117.cryptocompose.data.model.info.Coin
import com.msd117.cryptocompose.data.model.info.ContractAddress
import com.msd117.cryptocompose.data.model.info.Platform
import com.msd117.cryptocompose.domain.usecase.info.FetchInfoUseCase
import com.msd117.cryptocompose.presentation.detail.model.CoinDetail
import com.msd117.cryptocompose.presentation.detail.model.CoinPlatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import com.msd117.cryptocompose.presentation.detail.model.ContractAddress as DomainContractAddress

class FetchCoinDetailInfoHelper @Inject constructor(private val fetchInfoUseCase: FetchInfoUseCase) {

    suspend operator fun invoke(symbol: String): CoinDetail = withContext(Dispatchers.IO) {
        fetchInfoUseCase(symbol)?.let { coinData ->
            with(coinData) {
                CoinDetail(
                    logo = logo,
                    id = id,
                    name = name,
                    symbol = symbol,
                    slug = slug,
                    description = description,
                    notice = notice,
                    dateAdded = dateAdded,
                    tags = tags,
                    category = category,
                    platform = platform?.toDomain(),
                    website = urls.website,
                    technicalDoc = urls.technicalDoc,
                    twitter = urls.twitter,
                    reddit = urls.reddit,
                    messageBoard = urls.messageBoard,
                    announcement = urls.announcement,
                    chat = urls.chat,
                    explorer = urls.explorer,
                    sourceCode = urls.sourceCode,
                    subreddit = subreddit,
                    tagNames = tagNames,
                    tagGroups = tagGroups,
                    twitterUsername = twitterUsername,
                    isHidden = isHidden,
                    dateLaunched = dateLaunched,
                    contractAddress = contractAddress.toDomain(),
                    selfReportedCirculatingSupply = selfReportedCirculatingSupply,
                    selfReportedTags = selfReportedTags
                )
            }
        } ?: throw IOException()
    }

    private fun Platform.toDomain(): CoinPlatform {
        return CoinPlatform(
            name = name,
            coin = coin?.toDomain()
        )
    }

    private fun List<ContractAddress>.toDomain(): List<DomainContractAddress> {
        return map { contractAddress ->
            DomainContractAddress(
                contractAddress = contractAddress.contractAddress,
                platform = contractAddress.platform.toDomain()
            )
        }
    }

    private fun Coin.toDomain(): com.msd117.cryptocompose.presentation.detail.model.Coin {
        return com.msd117.cryptocompose.presentation.detail.model.Coin(
            id = id,
            name = name,
            symbol = symbol,
            slug = slug
        )
    }
}
