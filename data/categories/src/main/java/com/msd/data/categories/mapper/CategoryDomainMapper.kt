package com.msd.data.categories.mapper

import com.msd.categories.model.CategoryDomain
import com.msd.data.categories.model.CategoryData

fun List<CategoryData>.toDomain(): List<CategoryDomain> {
    return mapNotNull { categoryData ->
        with(categoryData) {
            if (!id.isNullOrEmpty() && !name.isNullOrEmpty() && !title.isNullOrEmpty()) {
                CategoryDomain(
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
            } else {
                null
            }
        }
    }
}
