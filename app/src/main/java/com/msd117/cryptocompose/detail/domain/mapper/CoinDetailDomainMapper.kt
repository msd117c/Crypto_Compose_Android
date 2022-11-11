package com.msd117.cryptocompose.detail.domain.mapper

import com.msd117.cryptocompose.detail.data.model.CoinDetail
import com.msd117.cryptocompose.detail.data.model.Urls
import com.msd117.cryptocompose.detail.domain.model.CoinDetailDomain
import com.msd117.cryptocompose.detail.domain.model.UrlsDomain

fun CoinDetail.toDomain(): CoinDetailDomain {
    return CoinDetailDomain(
        name = name,
        symbol = symbol,
        description = description,
        dateAdded = dateAdded,
        tagNames = tagNames,
        urls = urls.toDomain()
    )
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
