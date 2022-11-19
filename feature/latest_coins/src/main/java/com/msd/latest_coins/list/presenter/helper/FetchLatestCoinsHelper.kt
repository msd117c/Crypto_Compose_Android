package com.msd.latest_coins.list.presenter.helper

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msd.latest_coins.list.presenter.model.LatestCoin
import com.msd.latest_coins.list.presenter.LatestCoinsState
import com.msd.latest_coins_list.FetchLatestCoinsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class FetchLatestModelsHelper @Inject constructor(
    private val fetchLatestCoinsUseCase: FetchLatestCoinsUseCase
) {

    operator fun invoke(sortBy: LatestCoinsState.Loaded.SortBy): Flow<PagingData<LatestCoin>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            LatestPagingSourceHelper(fetchLatestCoinsUseCase, sortBy.id)
        }.flow
    }
}
