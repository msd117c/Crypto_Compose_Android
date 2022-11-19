package com.msd117.cryptocompose

import com.msd.latest_coins_list.presenter.LatestCoinsState
import com.msd.latest_coins_list.presenter.helper.SortByOption
import com.msd117.cryptocompose.latest.presenter.model.Growth
import com.msd.latest_coins_list.presenter.model.LatestCoin

class TestModels {

    object LatestCoinModels {
        val latestCoin = com.msd.latest_coins_list.presenter.model.LatestCoin(
            id = 0,
            name = "Name",
            symbol = "Symbol",
            summary = "Summary",
            growth = Growth.NONE,
            price = "Price",
            icon = "Icon",
            slug = null,
            cmcRank = null,
            numMarketPairs = null,
            circulatingSupply = null,
            totalSupply = null,
            maxSupply = null,
            lastUpdated = null,
            dateAdded = null,
            tags = null,
            platform = null,
            btc = null,
            eth = null,
            usd = null
        )

        val sortByOptions = com.msd.latest_coins_list.presenter.helper.SortByOption.values().map { sortByOption ->
            with(sortByOption) {
                com.msd.latest_coins_list.presenter.LatestCoinsState.Loaded.SortBy(
                    id,
                    label = label,
                    selected = sortByOption == com.msd.latest_coins_list.presenter.helper.SortByOption.MARKET_CAP
                )
            }
        }
    }
}
