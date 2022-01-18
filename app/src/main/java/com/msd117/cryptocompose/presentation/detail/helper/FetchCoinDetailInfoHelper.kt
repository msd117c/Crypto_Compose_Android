package com.msd117.cryptocompose.presentation.detail.helper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.data.model.info.Coin
import com.msd117.cryptocompose.data.model.info.ContractAddress
import com.msd117.cryptocompose.data.model.info.Platform
import com.msd117.cryptocompose.data.model.info.Urls
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
                    tagNames = tagNames,
                    tagGroups = tagGroups,
                    twitterUsername = twitterUsername,
                    isHidden = isHidden,
                    dateLaunched = dateLaunched,
                    contractAddress = contractAddress.toDomain(),
                    selfReportedCirculatingSupply = selfReportedCirculatingSupply,
                    selfReportedTags = selfReportedTags,
                    technicalButtons = urls.toDomainTechnicalButtons(),
                    urlButtons = urls.toDomainUrlButtons()
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

    private fun Urls.toDomainTechnicalButtons(): List<CoinDetail.TechnicalButtons> {
        return mutableListOf<CoinDetail.TechnicalButtons>().apply {
            addIfValid(
                R.drawable.ic_source_code,
                R.string.details_source_code_button,
                sourceCode.firstOrNull()
            )
            addIfValid(
                R.drawable.ic_technical_doc,
                R.string.details_technical_docs_button,
                technicalDoc.firstOrNull()
            )
        }
    }

    private fun MutableList<CoinDetail.TechnicalButtons>.addIfValid(
        @DrawableRes icon: Int,
        @StringRes label: Int,
        url: String?
    ) {
        url?.let { add(CoinDetail.TechnicalButtons(icon, label, url)) }
    }

    private fun Urls.toDomainUrlButtons(): List<CoinDetail.UrlButton> {
        return mutableListOf<CoinDetail.UrlButton>().apply {
            addIfValid(R.drawable.ic_twitter, twitter.firstOrNull())
            addIfValid(R.drawable.ic_announcement, announcement.firstOrNull())
            addIfValid(R.drawable.ic_browser, explorer.firstOrNull())
            addIfValid(R.drawable.ic_message, messageBoard.firstOrNull())
            addIfValid(R.drawable.ic_chat, chat.firstOrNull())
            addIfValid(R.drawable.ic_reddit, reddit.firstOrNull())
        }
    }

    private fun MutableList<CoinDetail.UrlButton>.addIfValid(@DrawableRes icon: Int, url: String?) {
        url?.let { add(CoinDetail.UrlButton(icon, url)) }
    }
}
