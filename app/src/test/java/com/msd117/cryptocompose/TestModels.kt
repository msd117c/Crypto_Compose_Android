package com.msd117.cryptocompose

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
            icon = "Icon"
        )
    }
}
