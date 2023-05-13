package com.msd.data.coin_details

import com.msd.data.coin_details.model.CoinDetail
import com.msd.data.coin_details.model.Urls
import com.msd.domain.coin_details.model.CoinDetailDomain
import com.msd.domain.coin_details.model.UrlsDomain

object TestDataBuilder {

    fun buildCoinDetail(
        urls: Urls = buildUrls(),
        name: String = "Name",
        symbol: String = "Symbol",
        slug: String = "Slug",
        description: String = "Description",
        dateAdded: String = "DateAdded",
        tagNames: List<String> = listOf("TagName")
    ): CoinDetail {
        return CoinDetail(
            urls = urls,
            name = name,
            symbol = symbol,
            slug = slug,
            description = description,
            dateAdded = dateAdded,
            tagNames = tagNames
        )
    }

    fun buildUrls(
        website: List<String> = listOf("website"),
        twitter: List<String> = listOf("twitter"),
        messageBoard: List<String> = listOf("messageBoard"),
        chat: List<String> = listOf("chat"),
        facebook: List<String> = listOf("facebook"),
        explorer: List<String> = listOf("explorer"),
        reddit: List<String> = listOf("reddit"),
        technicalDoc: List<String> = listOf("technicalDoc"),
        sourceCode: List<String> = listOf("sourceCode"),
        announcement: List<String> = listOf("announcement")
    ): Urls {
        return Urls(
            website = website,
            twitter = twitter,
            messageBoard = messageBoard,
            chat = chat,
            facebook = facebook,
            explorer = explorer,
            reddit = reddit,
            technicalDoc = technicalDoc,
            sourceCode = sourceCode,
            announcement = announcement,
        )
    }

    fun buildCoinDetailDomain(
        name: String = "Name",
        symbol: String = "Symbol",
        description: String = "Description",
        dateAdded: String = "DateAdded",
        tagNames: List<String> = listOf("TagName"),
        urls: UrlsDomain = buildUrlsDomain()
    ): CoinDetailDomain {
        return CoinDetailDomain(
            name = name,
            symbol = symbol,
            description = description,
            dateAdded = dateAdded,
            tagNames = tagNames,
            urls = urls,
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

    const val coinDetailJson = "" +
            "{\n" +
            "   \"symbol\":\"Symbol\",\n" +
            "   \"self_reported_tags\":null,\n" +
            "   \"tag-groups\":[\n" +
            "      \n" +
            "   ],\n" +
            "   \"twitter_username\":\"\",\n" +
            "   \"is_hidden\":0,\n" +
            "   \"description\":\"Description\",\n" +
            "   \"tag-names\":[\n" +
            "      \n" +
            "   ],\n" +
            "   \"self_reported_circulating_supply\":null,\n" +
            "   \"contract_address\":[\n" +
            "      \n" +
            "   ],\n" +
            "   \"subreddit\":\"\",\n" +
            "   \"platform\":null,\n" +
            "   \"tags\":[\n" +
            "      \"TagName\"\n" +
            "   ],\n" +
            "   \"date_added\":\"DateAdded\",\n" +
            "   \"urls\":{\n" +
            "      \"website\":[\n" +
            "         \"website\"\n" +
            "      ],\n" +
            "      \"twitter\":[\n" +
            "         \"twitter\"\n" +
            "      ],\n" +
            "      \"message_board\":[\n" +
            "         \"message_board\"\n" +
            "      ],\n" +
            "      \"chat\":[\n" +
            "         \"chat\"\n" +
            "      ],\n" +
            "      \"facebook\":[\n" +
            "         \"facebook\"\n" +
            "      ],\n" +
            "      \"explorer\":[\n" +
            "         \"explorer\"\n" +
            "      ],\n" +
            "      \"reddit\":[\n" +
            "         \"reddit\"\n" +
            "      ],\n" +
            "      \"technical_doc\":[\n" +
            "         \"technical_doc\"\n" +
            "      ],\n" +
            "      \"source_code\":[\n" +
            "         \"source_code\"\n" +
            "      ],\n" +
            "      \"announcement\":[\n" +
            "         \"announcement\"\n" +
            "      ]\n" +
            "   },\n" +
            "   \"name\":\"Name\",\n" +
            "   \"logo\":\"\",\n" +
            "   \"id\":1,\n" +
            "   \"self_reported_market_cap\":null,\n" +
            "   \"category\":\"\",\n" +
            "   \"slug\":\"slug\",\n" +
            "   \"date_launched\":null,\n" +
            "   \"notice\":\"\"\n" +
            "}\n"

    const val coinDetailResponseJson = "" +
            "{\n" +
            "   \"status\":{\n" +
            "      \"timestamp\":\"2022-12-12T13:16:55.326Z\",\n" +
            "      \"error_code\":0,\n" +
            "      \"error_message\":null,\n" +
            "      \"elapsed\":17,\n" +
            "      \"credit_count\":1,\n" +
            "      \"notice\":null\n" +
            "   },\n" +
            "   \"data\":{\n" +
            "\"Symbol\":$coinDetailJson\n" +
            "}\n" +
            "}"
}