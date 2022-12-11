package com.msd.categories.presenter.mapper

import com.msd.categories.TestDataBuilder.buildCategory
import com.msd.categories.TestDataBuilder.buildCategoryDomain
import com.msd.categories.presenter.mapper.CategoryMapper.toPresentation
import org.junit.Test

class CategoryMapperTest {

    @Test
    fun `when mapping from domain to presentation should return the expected values`() {
        val domainModels = listOf(buildCategoryDomain())
        val expectedModels = listOf(buildCategory())

        val result = domainModels.toPresentation()

        assert(result == expectedModels)
    }
}