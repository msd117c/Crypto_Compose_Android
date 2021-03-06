package com.msd117.cryptocompose.presentation.detail.presenter

import com.msd117.cryptocompose.presentation.detail.helper.FetchCoinDetailInfoHelper
import com.msd117.cryptocompose.presentation.detail.model.CoinDetail
import com.msd117.cryptocompose.presentation.detail.model.CoinPlatform
import com.msd117.cryptocompose.presentation.detail.model.ContractAddress
import com.msd117.cryptocompose.utils.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest : ViewModelTest<CoinDetailViewModel>() {

    private val fetchCoinDetailInfoHelper: FetchCoinDetailInfoHelper = mock()
    private val symbol = "BTC"
    private val icon = "icon"
    private val name = "Bitcoin"

    override val viewModel = CoinDetailViewModel(
        coroutineScope = scope,
        fetchCoinDetailInfoHelper = fetchCoinDetailInfoHelper,
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
        runBlockingTest {
            whenever(fetchCoinDetailInfoHelper(symbol)).thenReturn(coinDetail)
            val expectedStates = listOf(
                CoinDetailState.Uninitialized,
                CoinDetailState.Loading,
                CoinDetailState.Loaded(coinDetail)
            )
            val states = mutableListOf<CoinDetailState>()
            launch { viewModel.getState().toCollection(states) }.apply {

                viewModel.initialize()

                assert(expectedStates == states)
                verify(fetchCoinDetailInfoHelper, only()).invoke(symbol)
                cancel()
            }
        }
    }

    @Test
    fun `when initializing with errors should return the expected states`() {
        runBlockingTest {
            whenever(fetchCoinDetailInfoHelper(symbol)).thenAnswer { throw Exception() }
            val expectedStates = listOf(
                CoinDetailState.Uninitialized,
                CoinDetailState.Loading,
                CoinDetailState.Error
            )
            val states = mutableListOf<CoinDetailState>()
            launch { viewModel.getState().toCollection(states) }.apply {

                viewModel.initialize()

                assert(expectedStates == states)
                verify(fetchCoinDetailInfoHelper, only()).invoke(symbol)
                cancel()
            }
        }
    }

    @Test
    fun `when already initialized should not initialize again`() {
        runBlockingTest {
            whenever(fetchCoinDetailInfoHelper(symbol)).thenAnswer { throw Exception() }
            viewModel.initialize()
            val expectedStates = listOf(CoinDetailState.Error)
            val states = mutableListOf<CoinDetailState>()
            launch { viewModel.getState().toCollection(states) }.apply {

                viewModel.initialize()

                assert(expectedStates == states)
                verify(fetchCoinDetailInfoHelper, only()).invoke(symbol)
                cancel()
            }
        }
    }
}