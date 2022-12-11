package com.msd.categories

import com.msd.categories.model.CategoryDomain
import com.msd.categories.presenter.model.Category

object TestDataBuilder {

    fun buildCategoryDomain(
        id: String = "id",
        name: String = "Name",
        title: String = "Title",
        description: String? = "Description",
        numTokens: Int? = 1,
        avgPriceChange: Double? = 0.0,
        marketCap: Double? = 0.0,
        marketCapChange: Double? = 0.0,
        volume: Double? = 0.0,
        volumeChange: Double? = 0.0,
        lastUpdated: String? = "never",
    ): CategoryDomain {
        return CategoryDomain(
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
            lastUpdated = lastUpdated,
        )
    }

    fun buildCategory(
        id: String = "id",
        name: String = "Name",
        title: String = "Title",
        description: String? = "Description",
        numTokens: Int? = 1,
        avgPriceChange: Double? = 0.0,
        marketCap: Double? = 0.0,
        marketCapChange: Double? = 0.0,
        volume: Double? = 0.0,
        volumeChange: Double? = 0.0,
        lastUpdated: String? = "never",
    ): Category {
        return Category(
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
            lastUpdated = lastUpdated,
        )
    }
}