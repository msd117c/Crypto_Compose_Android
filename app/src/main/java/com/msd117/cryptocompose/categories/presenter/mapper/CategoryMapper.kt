package com.msd117.cryptocompose.categories.presenter.mapper

import com.msd117.cryptocompose.categories.domain.model.CategoryDomain
import com.msd117.cryptocompose.categories.presenter.model.Category

fun List<CategoryDomain>.toPresentation(): List<Category> {
    return map { categoryDomain ->
        with(categoryDomain) {
            Category(
                id = id,
                name = name,
                title = title,
                description = description,
                numTokens = numTokens,
                avgPriceChange = avgPriceChange,
                marketCap = marketCap,
                marketCapChange = marketCapChange,
                volume = volume,
                volumeChange = volumeChange,
                lastUpdated = lastUpdated
            )
        }
    }
}
