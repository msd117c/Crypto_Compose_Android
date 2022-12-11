package com.msd.latest_coins.common

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.msd.coin_details.model.CoinDetailDomain
import com.msd.coin_details.model.UrlsDomain
import com.msd.latest_coins.R
import com.msd.latest_coins.detail.presenter.model.CoinDetail
import com.msd.latest_coins.list.presenter.LatestCoinsState
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.latest_coins_list.model.GrowthDomain
import com.msd.latest_coins_list.model.LatestCoinDomain

object TestDataBuilder {

    fun buildLatestCoinDomain(
        id: Int = 1,
        name: String = "Name",
        symbol: String = "Symbol",
        summary: String = "Summary",
        growth: GrowthDomain = GrowthDomain.NONE,
        price: String = "0",
        icon: String = "icon",
        cmcRank: Int = 1,
    ): LatestCoinDomain {
        return LatestCoinDomain(
            id = id,
            name = name,
            symbol = symbol,
            summary = summary,
            growth = growth,
            price = price,
            icon = icon,
            cmcRank = cmcRank,
        )
    }

    fun buildLatestCoin(
        id: Int = 1,
        name: String = "Name",
        symbol: String = "Symbol",
        summary: String = "Summary",
        growthColor: Color = Color.Gray,
        price: String = "0",
        icon: String = "icon",
        cmcRank: String = "1",
    ): LatestCoin {
        return LatestCoin(
            id = id,
            name = name,
            symbol = symbol,
            summary = summary,
            growthColor = growthColor,
            price = price,
            icon = icon,
            cmcRank = cmcRank,
        )
    }

    fun buildSortByOption(
        id: String = "",
        @StringRes label: Int = -1,
        selected: Boolean = true
    ): LatestCoinsState.Loaded.SortBy {
        return LatestCoinsState.Loaded.SortBy(id, label, selected)
    }

    fun buildCoinDetailDomain(
        name: String = "Name",
        symbol: String = "Symbol",
        description: String = "Description",
        dateAdded: String = "2022-12-10T21:41:00.000Z",
        tagNames: List<String> = listOf("Tag name"),
        urls: UrlsDomain = buildUrlsDomain()
    ): CoinDetailDomain {
        return CoinDetailDomain(
            name = name,
            symbol = symbol,
            description = description,
            dateAdded = dateAdded,
            tagNames = tagNames,
            urls = urls
        )
    }

    fun buildUrlsDomain(
        website: String = "website",
        twitter: String = "twitter",
        messageBoard: String = "messageBoard",
        chat: String = "chat",
        facebook: String = "facebook",
        explorer: String = "explorer",
        reddit: String = "reddit",
        technicalDoc: String = "technicalDoc",
        sourceCode: String = "sourceCode",
        announcement: String = "announcement"
    ): UrlsDomain {
        return UrlsDomain(
            website = website,
            twitter = twitter,
            messageBoard = messageBoard,
            chat = chat,
            facebook = facebook,
            explorer = explorer,
            reddit = reddit,
            technicalDoc = technicalDoc,
            sourceCode = sourceCode,
            announcement = announcement
        )
    }

    fun buildCoinDetail(
        name: String = "Name",
        symbol: String = "Symbol",
        description: String = "Description",
        dateAdded: String = "10 December 2022",
        tagNames: List<String> = listOf("Tag name"),
        technicalButtons: List<CoinDetail.TechnicalButtons> = buildTechnicalButtons(),
        urlButtons: List<CoinDetail.UrlButton> = buildUrlButtons()
    ): CoinDetail {
        return CoinDetail(
            name = name,
            symbol = symbol,
            description = description,
            dateAdded = dateAdded,
            tagNames = tagNames,
            technicalButtons = technicalButtons,
            urlButtons = urlButtons
        )
    }

    fun buildTechnicalButtons(
        sourceCode: String = "sourceCode",
        technicalDoc: String = "technicalDoc"
    ): List<CoinDetail.TechnicalButtons> {
        return listOfNotNull(
            CoinDetail.TechnicalButtons(
                R.drawable.ic_source_code,
                R.string.details_source_code_button,
                sourceCode
            ).takeUnless { sourceCode.isEmpty() },
            CoinDetail.TechnicalButtons(
                R.drawable.ic_technical_doc,
                R.string.details_technical_docs_button,
                technicalDoc
            ).takeUnless { technicalDoc.isEmpty() }
        )
    }

    fun buildUrlButtons(
        twitter: String = "twitter",
        announcement: String = "announcement",
        explorer: String = "explorer",
        messageBoard: String = "messageBoard",
        chat: String = "chat",
        reddit: String = "reddit"
    ): List<CoinDetail.UrlButton> {
        return listOfNotNull(
            CoinDetail.UrlButton(R.drawable.ic_twitter, twitter).takeUnless { twitter.isEmpty() },
            CoinDetail.UrlButton(R.drawable.ic_announcement, announcement)
                .takeUnless { announcement.isEmpty() },
            CoinDetail.UrlButton(R.drawable.ic_browser, explorer).takeUnless { explorer.isEmpty() },
            CoinDetail.UrlButton(R.drawable.ic_message, messageBoard)
                .takeUnless { messageBoard.isEmpty() },
            CoinDetail.UrlButton(R.drawable.ic_chat, chat).takeUnless { chat.isEmpty() },
            CoinDetail.UrlButton(R.drawable.ic_reddit, reddit).takeUnless { reddit.isEmpty() }
        )
    }
}