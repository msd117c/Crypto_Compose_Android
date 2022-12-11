package com.msd.latest_coins.list.presenter.helper

import com.msd.latest_coins.R
import com.msd.latest_coins.common.TestDataBuilder.buildSortByOption
import org.junit.Test

private const val NAME_ID = "name"
private const val SYMBOL_ID = "symbol"
private const val DATE_ADDED_ID = "date_added"
private const val MARKET_CAP_ID = "market_cap"
private const val MARKET_CAP_STRICT_ID = "market_cap_strict"
private const val PRICE_ID = "price"
private const val CIRCULATING_SUPPLY_ID = "circulating_supply"
private const val TOTAL_SUPPLY_ID = "total_supply"
private const val MAX_SUPPLY_ID = "max_supply"
private const val NUM_MARKET_PAIRS_ID = "num_market_pairs"
private const val VOLUME_24H_ID = "volume_24h"
private const val PERCENT_CHANGE_1H_ID = "percent_change_1h"
private const val PERCENT_CHANGE_24H_ID = "percent_change_24h"
private const val PERCENT_CHANGE_7D_ID = "percent_change_7d"
private const val MARKET_CAP_BY_TOTAL_SUPPLY_STRICT_ID = "market_cap_by_total_supply_strict"
private const val VOLUME_7D_ID = "volume_7d"
private const val VOLUME_30D_ID = "volume_30d"

class GetLatestCoinSortByOptionsHelperTest {

    private val helper = GetLatestCoinSortByOptionsHelper()

    @Test
    fun `when retrieving the sortBy options should return the expected list`() {
        val expectedValues = listOf(
            buildSortByOption(MARKET_CAP_ID, R.string.sort_by_market_cap, true),
            buildSortByOption(NAME_ID, R.string.sort_by_name, false),
            buildSortByOption(SYMBOL_ID, R.string.sort_by_symbol, false),
            buildSortByOption(DATE_ADDED_ID, R.string.sort_by_date_added, false),
            buildSortByOption(MARKET_CAP_STRICT_ID, R.string.sort_by_market_cap_strict, false),
            buildSortByOption(PRICE_ID, R.string.sort_by_price, false),
            buildSortByOption(CIRCULATING_SUPPLY_ID, R.string.sort_by_circulating_supply, false),
            buildSortByOption(TOTAL_SUPPLY_ID, R.string.sort_by_total_supply, false),
            buildSortByOption(MAX_SUPPLY_ID, R.string.sort_by_max_supply, false),
            buildSortByOption(NUM_MARKET_PAIRS_ID, R.string.sort_by_num_market_pairs, false),
            buildSortByOption(VOLUME_24H_ID, R.string.sort_by_volume_24h, false),
            buildSortByOption(PERCENT_CHANGE_1H_ID, R.string.sort_by_percent_change_1h, false),
            buildSortByOption(PERCENT_CHANGE_24H_ID, R.string.sort_by_percent_change_24h, false),
            buildSortByOption(PERCENT_CHANGE_7D_ID, R.string.sort_by_percent_change_7d, false),
            buildSortByOption(
                MARKET_CAP_BY_TOTAL_SUPPLY_STRICT_ID,
                R.string.sort_by_market_cap_by_total_supply_strict,
                false
            ),
            buildSortByOption(VOLUME_7D_ID, R.string.sort_by_volume_7d, false),
            buildSortByOption(VOLUME_30D_ID, R.string.sort_by_volume_30d, false)
        )

        val results = helper()

        assert(results.size == expectedValues.size)
        assert(results.containsAll(expectedValues))
        assert(results == expectedValues)
    }
}