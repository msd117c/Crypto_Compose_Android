package com.msd117.cryptocompose.detail.presenter.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.detail.domain.model.*
import com.msd117.cryptocompose.detail.presenter.model.Coin
import com.msd117.cryptocompose.detail.presenter.model.CoinDetail
import com.msd117.cryptocompose.detail.presenter.model.CoinPlatform
import com.msd117.cryptocompose.detail.presenter.model.ContractAddress

fun CoinDetailDomain.toPresentation(): CoinDetail {
    return CoinDetail(
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
        platform = platform?.toPresentation(),
        tagNames = tagNames,
        tagGroups = tagGroups,
        twitterUsername = twitterUsername,
        isHidden = isHidden,
        dateLaunched = dateLaunched,
        contractAddress = contractAddress.toPresentation(),
        selfReportedCirculatingSupply = selfReportedCirculatingSupply,
        selfReportedTags = selfReportedTags,
        technicalButtons = urls.toTechnicalButtons(),
        urlButtons = urls.toUrlButtons()
    )
}

private fun CoinPlatformDomain.toPresentation(): CoinPlatform {
    return CoinPlatform(name = name, coin = coinDomain?.toPresentation())
}

private fun CoinDomain.toPresentation(): Coin {
    return Coin(id = id, name = name, symbol = symbol, slug = slug)
}

private fun List<ContractAddressDomain>.toPresentation(): List<ContractAddress> {
    return map { contractAddress ->
        ContractAddress(
            contractAddress = contractAddress.contractAddress,
            platform = contractAddress.platform.toPresentation()
        )
    }
}

private fun UrlsDomain.toTechnicalButtons(): List<CoinDetail.TechnicalButtons> {
    return mutableListOf<CoinDetail.TechnicalButtons>().apply {
        addIfValid(R.drawable.ic_source_code, R.string.details_source_code_button, sourceCode)
        addIfValid(
            R.drawable.ic_technical_doc,
            R.string.details_technical_docs_button,
            technicalDoc
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

private fun UrlsDomain.toUrlButtons(): List<CoinDetail.UrlButton> {
    return mutableListOf<CoinDetail.UrlButton>().apply {
        addIfValid(R.drawable.ic_twitter, twitter)
        addIfValid(R.drawable.ic_announcement, announcement)
        addIfValid(R.drawable.ic_browser, explorer)
        addIfValid(R.drawable.ic_message, messageBoard)
        addIfValid(R.drawable.ic_chat, chat)
        addIfValid(R.drawable.ic_reddit, reddit)
    }
}

private fun MutableList<CoinDetail.UrlButton>.addIfValid(@DrawableRes icon: Int, url: String?) {
    url?.let { add(CoinDetail.UrlButton(icon, url)) }
}
