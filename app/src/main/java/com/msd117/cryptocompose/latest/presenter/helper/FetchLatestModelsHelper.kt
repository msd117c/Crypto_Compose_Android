package com.msd117.cryptocompose.latest.presenter.helper

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msd117.cryptocompose.latest.domain.FetchLatestUseCase
import com.msd117.cryptocompose.latest.presenter.model.LatestCoin
import com.msd117.cryptocompose.latest.presenter.LatestCoinsState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class FetchLatestModelsHelper @Inject constructor(private val fetchLatestUseCase: FetchLatestUseCase) {

    operator fun invoke(sortBy: LatestCoinsState.Loaded.SortBy): Flow<PagingData<LatestCoin>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            LatestPagingSourceHelper(fetchLatestUseCase, sortBy.id)
        }.flow
    }
}