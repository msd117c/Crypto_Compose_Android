package com.msd117.cryptocompose.detail.domain.mapper

import com.msd117.cryptocompose.detail.data.model.*
import com.msd117.cryptocompose.detail.domain.model.*

fun Data.toDomain(): CoinInfoDomain {
    return CoinInfoDomain(
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
        urls = urls.toDomain()
    )
}

private fun Platform.toDomain(): CoinPlatformDomain {
    return CoinPlatformDomain(
        name = name,
        coinDomain = coin?.toDomain()
    )
}

private fun Coin.toDomain(): CoinDomain {
    return CoinDomain(
        id = id,
        name = name,
        symbol = symbol,
        slug = slug
    )
}

private fun List<ContractAddress>.toDomain(): List<ContractAddressDomain> {
    return map { contractAddress ->
        ContractAddressDomain(
            contractAddress = contractAddress.contractAddress,
            platform = contractAddress.platform.toDomain()
        )
    }
}

private fun Urls.toDomain(): UrlsDomain {
    return UrlsDomain(
        website = website.firstOrNull().orEmpty(),
        twitter = twitter.firstOrNull().orEmpty(),
        messageBoard = messageBoard.firstOrNull().orEmpty(),
        chat = chat.firstOrNull().orEmpty(),
        facebook = facebook.firstOrNull().orEmpty(),
        explorer = explorer.firstOrNull().orEmpty(),
        reddit = reddit.firstOrNull().orEmpty(),
        technicalDoc = technicalDoc.firstOrNull().orEmpty(),
        sourceCode = sourceCode.firstOrNull().orEmpty(),
        announcement = announcement.firstOrNull().orEmpty()
    )
}
