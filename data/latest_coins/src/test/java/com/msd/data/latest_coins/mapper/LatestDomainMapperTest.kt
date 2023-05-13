package com.msd.data.latest_coins.mapper

import com.msd.data.latest_coins.TestDataBuilder.buildCoinData
import com.msd.data.latest_coins.TestDataBuilder.buildLatestCoinDomain
import com.msd.data.latest_coins.TestDataBuilder.buildQuote
import com.msd.data.latest_coins.TestDataBuilder.buildUsd
import com.msd.data.latest_coins.mapper.LatestDomainMapper.toDomain
import com.msd.data.latest_coins.model.CoinData
import com.msd.domain.latest_coins_list.model.GrowthDomain
import com.msd.domain.latest_coins_list.model.LatestCoinDomain
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LatestDomainMapperTest(private val testData: TestData) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun initParameters(): Collection<TestData> {
            val usdDataOptions = mapOf(
                (buildUsd() to ("$ 0.00" to "0.00%")),
                (buildUsd(price = 1.0, percentChange1h = 1.0) to ("$ 1.00" to "+1.00%")),
                (buildUsd(price = 1.0, percentChange1h = -1.0) to ("$ 1.00" to "-1.00%")),
                (buildUsd(null, null) to ("$ 0.00" to "")),
                (null to ("$ 0.00" to ""))
            )
            val cmcRankOptions = listOf(0, null)
            val idOptions = listOf(0, null)
            val nullFieldsOptions = listOf(true, false)

            return nullFieldsOptions.flatMap { nullFields ->
                usdDataOptions.flatMap { usdData ->
                    val (usd, summaryAndPrice) = usdData
                    val (price, summary) = summaryAndPrice
                    cmcRankOptions.flatMap { cmcRank ->
                        idOptions.map { id ->
                            TestData(
                                dataModel = buildCoinData(
                                    id = id,
                                    name = "Name".takeUnless { nullFields },
                                    symbol = "Symbol".takeUnless { nullFields },
                                    slug = "Slug".takeUnless { nullFields },
                                    cmcRank = cmcRank,
                                    quote = buildQuote(usd).takeUnless { nullFields }
                                ),
                                expectedModel = when {
                                    id == null || cmcRank == null -> null
                                    else -> buildLatestCoinDomain(
                                        id = id.toInt(),
                                        name = "Name".takeUnless { nullFields }.orEmpty(),
                                        symbol = "Symbol".takeUnless { nullFields }.orEmpty(),
                                        summary = summary.takeUnless { nullFields }.orEmpty(),
                                        growth = when {
                                            usd?.percentChange1h == null || nullFields -> GrowthDomain.NONE
                                            (usd.percentChange1h as Double) > 0.0 -> GrowthDomain.POSITIVE
                                            (usd.percentChange1h as Double) < 0.0 -> GrowthDomain.NEGATIVE
                                            else -> GrowthDomain.NONE
                                        },
                                        price = price.takeUnless { nullFields } ?: "$ 0.00",
                                        icon = "https://raw.githubusercontent.com/ErikThiart/cryptocurrency-icons/master/64/name.png".takeUnless { nullFields }
                                            .orEmpty(),
                                        cmcRank = cmcRank
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    data class TestData(
        val dataModel: CoinData,
        val expectedModel: LatestCoinDomain?
    )

    @Test
    fun `when mapping from data to domain should return the expected model`() = with(testData) {

        val result = listOf(dataModel).toDomain()

        assert(result == listOfNotNull(expectedModel))
    }
}