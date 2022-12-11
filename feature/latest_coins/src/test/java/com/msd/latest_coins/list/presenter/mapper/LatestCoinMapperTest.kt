package com.msd.latest_coins.list.presenter.mapper

import androidx.compose.ui.graphics.Color
import com.msd.latest_coins.common.TestDataBuilder.buildLatestCoin
import com.msd.latest_coins.common.TestDataBuilder.buildLatestCoinDomain
import com.msd.latest_coins.list.presenter.mapper.LatestCoinMapper.toPresentation
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.domain.latest_coins_list.model.GrowthDomain
import com.msd.domain.latest_coins_list.model.LatestCoinDomain
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LatestCoinMapperTest(private val testData: TestData) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun initParameters(): Collection<TestData> {
            val growthDomainOptions = GrowthDomain.values()

            return growthDomainOptions.map { growthDomain ->
                val growthColor = when (growthDomain) {
                    GrowthDomain.POSITIVE -> Color.Green
                    GrowthDomain.NEGATIVE -> Color.Red
                    GrowthDomain.NONE -> Color.Gray
                }

                TestData(
                    domainModel = listOf(buildLatestCoinDomain(growth = growthDomain)),
                    expected = buildLatestCoin(growthColor = growthColor)
                )
            }
        }
    }

    data class TestData(val domainModel: List<LatestCoinDomain>, val expected: LatestCoin)

    @Test
    fun `when mapping from domain should return the expected model`() = with(testData) {

        val result = domainModel.toPresentation().first()

        assert(result == expected)
    }
}