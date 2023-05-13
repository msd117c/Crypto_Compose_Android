package com.msd.data.coin_details.mapper

import com.msd.data.coin_details.TestDataBuilder.buildCoinDetail
import com.msd.data.coin_details.TestDataBuilder.buildCoinDetailDomain
import com.msd.data.coin_details.TestDataBuilder.buildUrls
import com.msd.data.coin_details.TestDataBuilder.buildUrlsDomain
import com.msd.data.coin_details.mapper.CoinDetailDomainMapper.toDomain
import org.junit.Test

class CoinDetailDomainMapperTest {

    @Test
    fun `when mapping coin detail data to domain should return the expected model`() {
        val coinDetail = buildCoinDetail()
        val expectedResult = buildCoinDetailDomain()

        val result = coinDetail.toDomain()

        assert(result == expectedResult)
    }

    @Test
    fun `when mapping coin detail data with more urls to domain should return the expected model`() {
        val coinDetail = buildCoinDetail(
            urls = buildUrls(
                website = listOf("website", "website2"),
                twitter = listOf("twitter", "twitter2"),
                messageBoard = listOf("messageBoard", "messageBoard2"),
                chat = listOf("chat", "chat2"),
                facebook = listOf("facebook", "facebook2"),
                explorer = listOf("explorer", "explorer2"),
                reddit = listOf("reddit", "reddit2"),
                technicalDoc = listOf("technicalDoc", "technicalDoc2"),
                sourceCode = listOf("sourceCode", "sourceCode2"),
                announcement = listOf("announcement", "announcement2")
            )
        )
        val expectedResult = buildCoinDetailDomain()

        val result = coinDetail.toDomain()

        assert(result == expectedResult)
    }

    @Test
    fun `when mapping coin detail data without urls to domain should return the expected model`() {
        val coinDetail = buildCoinDetail(
            urls = buildUrls(
                website = emptyList(),
                twitter = emptyList(),
                messageBoard = emptyList(),
                chat = emptyList(),
                facebook = emptyList(),
                explorer = emptyList(),
                reddit = emptyList(),
                technicalDoc = emptyList(),
                sourceCode = emptyList(),
                announcement = emptyList()
            )
        )
        val expectedResult = buildCoinDetailDomain(
            urls = buildUrlsDomain(
                website = "",
                twitter = "",
                messageBoard = "",
                chat = "",
                facebook = "",
                explorer = "",
                reddit = "",
                technicalDoc = "",
                sourceCode = "",
                announcement = ""
            )
        )

        val result = coinDetail.toDomain()

        assert(result == expectedResult)
    }
}