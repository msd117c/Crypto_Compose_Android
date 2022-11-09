package com.msd117.cryptocompose.categories.domain.mapper

import com.msd117.cryptocompose.categories.data.model.CategoryData
import com.msd117.cryptocompose.categories.domain.model.CategoryDomain

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
