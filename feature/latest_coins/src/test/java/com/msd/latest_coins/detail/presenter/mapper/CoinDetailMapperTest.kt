package com.msd.latest_coins.detail.presenter.mapper

import com.msd.domain.coin_details.model.CoinDetailDomain
import com.msd.latest_coins.common.TestDataBuilder.buildCoinDetail
import com.msd.latest_coins.common.TestDataBuilder.buildCoinDetailDomain
import com.msd.latest_coins.common.TestDataBuilder.buildTechnicalButtons
import com.msd.latest_coins.common.TestDataBuilder.buildUrlButtons
import com.msd.latest_coins.common.TestDataBuilder.buildUrlsDomain
import com.msd.latest_coins.detail.presenter.mapper.CoinDetailMapper.toPresentation
import com.msd.latest_coins.detail.presenter.model.CoinDetail
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

@RunWith(Parameterized::class)
class CoinDetailMapperTest(private val testData: TestData) {

    init {
        Locale.setDefault(Locale.UK)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun initParameters(): Collection<TestData> {
            val urlsDomainOptions = listOf(
                buildUrlsDomain(),
                buildUrlsDomain(
                    website = "",
                    twitter = "",
                    messageBoard = "",
                    chat = "",
                    facebook = "",
                    explorer = "",
                    reddit = "",
                    technicalDoc = "",
                    sourceCode = "",
                    announcement = "",
                )
            )

            return urlsDomainOptions.map { urlsDomain ->
                with(urlsDomain) {
                    val technicalButtons = buildTechnicalButtons(sourceCode, technicalDoc)
                    val urlButtons = buildUrlButtons(
                        twitter,
                        announcement,
                        explorer,
                        messageBoard,
                        chat,
                        reddit
                    )

                    TestData(
                        domainModel = buildCoinDetailDomain(urls = urlsDomain),
                        expected = buildCoinDetail(
                            technicalButtons = technicalButtons,
                            urlButtons = urlButtons
                        )
                    )
                }
            }
        }
    }

    data class TestData(val domainModel: CoinDetailDomain, val expected: CoinDetail)

    @Test
    fun `when mapping from domain should return the expected model`() = with(testData) {

        val result = domainModel.toPresentation()

        assert(result == expected)
    }
}