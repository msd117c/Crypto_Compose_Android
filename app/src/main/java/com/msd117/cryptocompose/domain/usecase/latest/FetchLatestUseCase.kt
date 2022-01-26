package com.msd117.cryptocompose.domain.usecase.latest

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.msd117.cryptocompose.data.repository.latest.LatestRepository
import retrofit2.Retrofit
import javax.inject.Inject

private const val PAGE_SIZE = 20

class FetchLatestUseCase @Inject constructor(private val retrofitClient: Retrofit) {

    operator fun invoke(sort: String) = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        LatestRepository(retrofitClient, sort)
    }.flow
}