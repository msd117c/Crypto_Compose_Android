package com.msd117.cryptocompose.domain.usecase.latest

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msd117.cryptocompose.data.model.latest.Data
import com.msd117.cryptocompose.data.repository.latest.LatestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLatestUseCase @Inject constructor(private val latestRepository: LatestRepository) {

    operator fun invoke(): Flow<PagingData<Data>> = Pager(PagingConfig(pageSize = 20)) {
        latestRepository
    }.flow
}