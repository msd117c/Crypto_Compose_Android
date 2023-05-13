package com.msd.data.categories

import com.msd.data.categories.model.CategoriesResponse
import com.msd.data.categories.model.CategoriesStatus
import com.msd.data.categories.model.CategoryData
import com.msd.domain.categories.model.CategoryDomain

object TestDataBuilder {

    fun buildCategoriesResponse(
        data: List<CategoryData> = listOf(buildCategoryData()),
        status: CategoriesStatus? = buildCategoriesStatus()
    ): CategoriesResponse {
        return CategoriesResponse(
            data = data,
            status = status
        )
    }

    fun buildCategoryData(
        id: String? = "id",
        name: String? = "Name",
        title: String? = "Title",
        description: String? = "Description",
        numTokens: Int? = 0,
        avgPriceChange: Double? = 0.0,
        marketCap: Double? = 0.0,
        marketCapChange: Double? = 0.0,
        volume: Double? = 0.0,
        volumeChange: Double? = 0.0,
        lastUpdated: String? = "LastUpdated"
    ): CategoryData {
        return CategoryData(
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

    fun buildCategoriesStatus(
        timestamp: String? = "Timestamp",
        errorCode: Int? = 0,
        errorMessage: String? = "ErrorMessage",
        elapsed: Int? = 0,
        creditCount: Int? = 0
    ): CategoriesStatus {
        return CategoriesStatus(
            timestamp = timestamp,
            errorCode = errorCode,
            errorMessage = errorMessage,
            elapsed = elapsed,
            creditCount = creditCount
        )
    }

    fun buildCategoryDomain(
        id: String = "id",
        name: String = "Name",
        title: String = "Title",
        description: String? = "Description",
        numTokens: Int? = 0,
        avgPriceChange: Double? = 0.0,
        marketCap: Double? = 0.0,
        marketCapChange: Double? = 0.0,
        volume: Double? = 0.0,
        volumeChange: Double? = 0.0,
        lastUpdated: String? = "LastUpdated"
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
            lastUpdated = lastUpdated
        )
    }
}