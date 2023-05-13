package com.msd.data.categories.mapper

import com.msd.data.categories.TestDataBuilder.buildCategoryData
import com.msd.data.categories.TestDataBuilder.buildCategoryDomain
import com.msd.data.categories.mapper.CategoryDomainMapper.toDomain
import com.msd.data.categories.model.CategoryData
import com.msd.domain.categories.model.CategoryDomain
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CategoryDomainMapperTest(private val testData: TestData) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun initParameters(): Collection<TestData> {
            val idOptions = listOf("id", "", null)
            val nameOptions = listOf("Name", "", null)
            val titleOptions = listOf("Title", "", null)

            return idOptions.flatMap { id ->
                nameOptions.flatMap { name ->
                    titleOptions.map { title ->
                        val expectedResult = when {
                            !id.isNullOrEmpty() && !name.isNullOrEmpty() && !title.isNullOrEmpty() -> buildCategoryDomain()
                            else -> null
                        }

                        TestData(buildCategoryData(id, name, title), expectedResult)
                    }
                }
            }
        }
    }

    data class TestData(val dataModel: CategoryData, val expectedResult: CategoryDomain?)

    @Test
    fun `when mapping from data to domain should return the expected model`() = with(testData) {

        val result = dataModel.toDomain()

        assert(result == expectedResult)
    }
}