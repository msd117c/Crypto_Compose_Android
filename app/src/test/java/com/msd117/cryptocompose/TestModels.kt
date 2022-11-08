package com.msd117.cryptocompose

import com.msd117.cryptocompose.latest.presenter.LatestCoinsState
import com.msd117.cryptocompose.latest.presenter.helper.SortByOption
import com.msd117.cryptocompose.latest.presenter.model.Growth
import com.msd117.cryptocompose.latest.presenter.model.LatestCoin

class TestModels {

    object LatestCoinModels {
        val latestCoin = LatestCoin(
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

        val sortByOptions = SortByOption.values().map { sortByOption ->
            with(sortByOption) {
                LatestCoinsState.Loaded.SortBy(
                    id,
                    label = label,
                    selected = sortByOption == SortByOption.MARKET_CAP
                )
            }
        }
    }
}
