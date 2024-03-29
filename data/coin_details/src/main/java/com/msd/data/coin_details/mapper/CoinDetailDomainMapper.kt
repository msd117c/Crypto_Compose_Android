package com.msd.data.coin_details.mapper

import com.msd.data.coin_details.model.CoinDetail
import com.msd.data.coin_details.model.Urls
import com.msd.domain.coin_details.model.CoinDetailDomain
import com.msd.domain.coin_details.model.UrlsDomain

internal object CoinDetailDomainMapper {

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
}
