package com.msd117.cryptocompose.detail.presenter

import com.msd117.cryptocompose.detail.presenter.helper.FetchCoinDetailHelper
import com.msd117.cryptocompose.detail.presenter.model.CoinDetail
import com.msd117.cryptocompose.detail.presenter.model.CoinPlatform
import com.msd117.cryptocompose.detail.presenter.model.ContractAddress
import com.msd117.cryptocompose.utils.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest : ViewModelTest<CoinDetailViewModel>() {

    private val fetchCoinDetailHelper: FetchCoinDetailHelper = mock()
    private val symbol = "BTC"
    private val icon = "icon"
    private val name = "Bitcoin"

    override val viewModel = CoinDetailViewModel(
        coroutineScope = scope,
        fetchCoinDetailHelper = fetchCoinDetailHelper,
        symbol = symbol,
        icon = icon,
        name = name
    )

    private val coinDetail = CoinDetail(
        logo = "",
        id = 0,
        name = "Bitcoin",
        symbol = "BTC",
        slug = "slug",
        description = "description",
        notice = "notice",
        dateAdded = "",
        tags = emptyList(),
        category = "category",
        platform = CoinPlatform("Coin", null),
        tagNames = listOf("tagNames"),
        tagGroups = listOf("tagGroups"),
        twitterUsername = "twitterUsername",
        isHidden = 0,
        dateLaunched = "dateLaunched",
        contractAddress = listOf(ContractAddress("address", CoinPlatform("Coin", null))),
        selfReportedCirculatingSupply = "selfReportedCirculatingSupply",
        selfReportedTags = listOf("selfReportedTags"),
        technicalButtons = emptyList(),
        urlButtons = emptyList()
    )

    @Test
    fun `when initializing successfully should return the expected states`() {
        runTest {
            whenever(fetchCoinDetailHelper(symbol)).thenReturn(coinDetail)
            val expectedStates = listOf(
                CoinDetailState.Uninitialized,
                CoinDetailState.Loading,
                CoinDetailState.Loaded(coinDetail)
            )
            val states = mutableListOf<CoinDetailState>()
            launch { viewModel.getState().toCollection(states) }.apply {

                viewModel.initialize()

                assert(expectedStates == states)
                verify(fetchCoinDetailHelper, only()).invoke(symbol)
                cancel()
            }
        }
    }

    @Test
    fun `when initializing with errors should return the expected states`() {
        runTest {
            whenever(fetchCoinDetailHelper(symbol)).thenAnswer { throw Exception() }
            val expectedStates = listOf(
                CoinDetailState.Uninitialized,
                CoinDetailState.Loading,
                CoinDetailState.Error
            )
            val states = mutableListOf<CoinDetailState>()
            launch { viewModel.getState().toCollection(states) }.apply {

                viewModel.initialize()

                assert(expectedStates == states)
                verify(fetchCoinDetailHelper, only()).invoke(symbol)
                cancel()
            }
        }
    }

    @Test
    fun `when already initialized should not initialize again`() {
        runTest {
            whenever(fetchCoinDetailHelper(symbol)).thenAnswer { throw Exception() }
            viewModel.initialize()
            val expectedStates = listOf(CoinDetailState.Error)
            val states = mutableListOf<CoinDetailState>()
            launch { viewModel.getState().toCollection(states) }.apply {

                viewModel.initialize()

                assert(expectedStates == states)
                verify(fetchCoinDetailHelper, only()).invoke(symbol)
                cancel()
            }
        }
    }
}
