package com.msd.latest_coins.list.presenter.helper

import androidx.annotation.StringRes
import com.msd.latest_coins.R
import com.msd.latest_coins.list.presenter.LatestCoinsState
import javax.inject.Inject

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

class GetLatestCoinSortByOptionsHelper @Inject constructor() {

    operator fun invoke(): List<LatestCoinsState.Loaded.SortBy> {
        return SortByOption.values().map { sortByOption ->
            LatestCoinsState.Loaded.SortBy(
                id = sortByOption.id,
                label = sortByOption.label,
                selected = sortByOption == SortByOption.MARKET_CAP
            )
        }.sortedBy { !it.selected }
    }
}

enum class SortByOption(val id: String, @StringRes val label: Int) {
    NAME(id = NAME_ID, label = R.string.sort_by_name),
    SYMBOL(id = SYMBOL_ID, label = R.string.sort_by_symbol),
    DATE_ADDED(id = DATE_ADDED_ID, label = R.string.sort_by_date_added),
    MARKET_CAP(id = MARKET_CAP_ID, label = R.string.sort_by_market_cap),
    MARKET_CAP_STRICT(id = MARKET_CAP_STRICT_ID, label = R.string.sort_by_market_cap_strict),
    PRICE(id = PRICE_ID, label = R.string.sort_by_price),
    CIRCULATING_SUPPLY(id = CIRCULATING_SUPPLY_ID, label = R.string.sort_by_circulating_supply),
    TOTAL_SUPPLY(id = TOTAL_SUPPLY_ID, label = R.string.sort_by_total_supply),
    MAX_SUPPLY(id = MAX_SUPPLY_ID, label = R.string.sort_by_max_supply),
    NUM_MARKET_PAIRS(id = NUM_MARKET_PAIRS_ID, label = R.string.sort_by_num_market_pairs),
    VOLUME_24H(id = VOLUME_24H_ID, label = R.string.sort_by_volume_24h),
    PERCENT_CHANGE_1H(id = PERCENT_CHANGE_1H_ID, label = R.string.sort_by_percent_change_1h),
    PERCENT_CHANGE_24H(id = PERCENT_CHANGE_24H_ID, label = R.string.sort_by_percent_change_24h),
    PERCENT_CHANGE_7D(id = PERCENT_CHANGE_7D_ID, label = R.string.sort_by_percent_change_7d),
    MARKET_CAP_BY_TOTAL_SUPPLY_STRICT(
        id = MARKET_CAP_BY_TOTAL_SUPPLY_STRICT_ID,
        label = R.string.sort_by_market_cap_by_total_supply_strict
    ),
    VOLUME_7D(id = VOLUME_7D_ID, label = R.string.sort_by_volume_7d),
    VOLUME_30D(id = VOLUME_30D_ID, label = R.string.sort_by_volume_30d),
}
