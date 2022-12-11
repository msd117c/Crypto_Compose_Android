package com.msd.categories.presenter.mapper

import com.msd.categories.model.CategoryDomain
import com.msd.categories.presenter.model.Category

object CategoryMapper {

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
}
