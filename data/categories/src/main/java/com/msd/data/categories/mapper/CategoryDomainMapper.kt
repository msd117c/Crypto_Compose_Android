package com.msd.data.categories.mapper

import com.msd.data.categories.model.CategoryData
import com.msd.domain.categories.model.CategoryDomain

object CategoryDomainMapper {

    fun CategoryData.toDomain(): CategoryDomain? {
        return if (!id.isNullOrEmpty() && !name.isNullOrEmpty() && !title.isNullOrEmpty()) {
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
