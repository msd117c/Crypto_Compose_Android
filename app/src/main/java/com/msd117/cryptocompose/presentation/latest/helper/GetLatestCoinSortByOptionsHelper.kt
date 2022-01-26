package com.msd117.cryptocompose.presentation.latest.helper

import androidx.annotation.StringRes
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsState
import javax.inject.Inject

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
    NAME(id = "name", label = R.string.sort_by_name),
    SYMBOL(id = "symbol", label = R.string.sort_by_symbol),
    DATE_ADDED(id = "date_added", label = R.string.sort_by_date_added),
    MARKET_CAP(id = "market_cap", label = R.string.sort_by_market_cap),
    MARKET_CAP_STRICT(id = "market_cap_strict", label = R.string.sort_by_market_cap_strict),
    PRICE(id = "price", label = R.string.sort_by_price),
    CIRCULATING_SUPPLY(id = "circulating_supply", label = R.string.sort_by_circulating_supply),
    TOTAL_SUPPLY(id = "total_supply", label = R.string.sort_by_total_supply),
    MAX_SUPPLY(id = "max_supply", label = R.string.sort_by_max_supply),
    NUM_MARKET_PAIRS(id = "num_market_pairs", label = R.string.sort_by_num_market_pairs),
    VOLUME_24H(id = "volume_24h", label = R.string.sort_by_volume_24h),
    PERCENT_CHANGE_1H(id = "percent_change_1h", label = R.string.sort_by_percent_change_1h),
    PERCENT_CHANGE_24H(id = "percent_change_24h", label = R.string.sort_by_volume_24h),
    PERCENT_CHANGE_7D(id = "percent_change_7d", label = R.string.sort_by_percent_change_7d),
    MARKET_CAP_BY_TOTAL_SUPPLY_STRICT(
        id = "market_cap_by_total_supply_strict",
        label = R.string.sort_by_market_cap_by_total_supply_strict
    ),
    VOLUME_7D(id = "volume_7d", label = R.string.sort_by_volume_7d),
    VOLUME_30D(id = "volume_30d", label = R.string.sort_by_volume_30d),
}